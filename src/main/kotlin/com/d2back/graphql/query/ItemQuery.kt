package com.d2back.graphql.query

import com.d2back.graphql.entity.ExampleEntity
import com.d2back.service.ItemService
import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ItemQuery(val exampleService: ItemService) : Query {

    @GraphQLDescription("find items by name")
    fun getItemsByName(name: String): Mono<ExampleEntity> {
        return exampleService.getItemsByName(name)
    }
}
