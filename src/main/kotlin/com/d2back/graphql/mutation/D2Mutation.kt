package com.d2back.graphql.mutation

import com.d2back.graphql.entity.ExampleEntity
import com.d2back.service.ItemService
import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Mutation
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class D2Mutation(val exampleService: ItemService) : Mutation {

    @GraphQLDescription("get value")
    fun put(id: ExampleEntity): Mono<ExampleEntity> {
        return id.toMono()
    }
}
