package com.d2back.graphql.query

import com.d2back.graphql.entity.ExampleEntity
import com.d2back.service.ExampleEntityService
import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ExampleExternalJWTSecuredQuery(val exampleService: ExampleEntityService) : Query {

    @GraphQLDescription("get value")
    fun get(id: String): Mono<ExampleEntity> {
        return exampleService.get(id)
    }
}
