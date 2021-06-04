package com.d2back.service

import com.d2back.graphql.context.bffContext
import com.d2back.graphql.entity.ExampleEntity
import com.d2back.repository.model.UniqueItem
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class ExampleEntityService {

    fun get(exampleEntityId: String): Mono<ExampleEntity> {
        return Mono.deferWithContext { reactiveCtx ->
            val bffContext = reactiveCtx.bffContext()
            ExampleEntity(bffContext.correlationId, "dou").toMono()
        }
    }
}
