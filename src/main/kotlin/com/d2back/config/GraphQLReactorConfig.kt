package com.d2back.config

import com.d2back.graphql.context.BFFContext
import com.expediagroup.graphql.execution.FunctionDataFetcher
import com.expediagroup.graphql.execution.SimpleKotlinDataFetcherFactoryProvider
import com.expediagroup.graphql.federation.FederatedSchemaGeneratorHooks
import com.expediagroup.graphql.federation.execution.FederatedTypeRegistry
import com.expediagroup.graphql.spring.execution.GRAPHQL_CONTEXT_KEY
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetcherFactory
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.full.withNullability

@Component
class MonoSchemaGeneratorHook(federatedTypeRegistry: FederatedTypeRegistry) :
    FederatedSchemaGeneratorHooks(federatedTypeRegistry) {

    override fun willResolveMonad(type: KType): KType =
        when (type.classifier) {
            Mono::class -> parameterTypeButNullable(type)
            else -> type
        } ?: type

    private fun parameterTypeButNullable(type: KType) =
        type.arguments.first().type?.withNullability(true)
}

@Component
class MonoDataFetcherFactoryProvider(
    private val objectMapper: ObjectMapper
) : SimpleKotlinDataFetcherFactoryProvider(objectMapper) {

    override fun functionDataFetcherFactory(target: Any?, kFunction: KFunction<*>): DataFetcherFactory<Any?> = DataFetcherFactory<Any?> {
        MonoDataFetcherWithContext(
            target = target,
            fn = kFunction,
            objectMapper = objectMapper
        )
    }
}

class MonoDataFetcherWithContext(target: Any?, fn: KFunction<*>, objectMapper: ObjectMapper) : FunctionDataFetcher(target, fn, objectMapper) {
    override fun get(environment: DataFetchingEnvironment): Any? =
        when (val result = super.get(environment)) {
            is Mono<*> -> addBffContext(result, environment.getContext())
                .toFuture()
            else -> result
        }

    private fun addBffContext(mono: Mono<*>, bffContext: BFFContext) =
        mono.subscriberContext { ctx ->
            ctx.put(GRAPHQL_CONTEXT_KEY, bffContext)
        }
}
