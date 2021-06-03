package com.d2back.unit.service

import com.d2back.graphql.context.AppUser
import com.d2back.graphql.context.BFFContext
import com.d2back.graphql.entity.ExampleEntity
import com.d2back.service.ExampleEntityService
import com.expediagroup.graphql.spring.execution.GRAPHQL_CONTEXT_KEY
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reactor.core.publisher.Mono

@ExtendWith(MockKExtension::class)
class ExampleEntityServiceTest {

    @InjectMockKs
    lateinit var exampleEntityService: ExampleEntityService

    @Test
    fun `get with existing id`() {
        val existingId = "example correlationId"
        val existingExampleEntity = ExampleEntity(existingId, "dou")

        val exampleEntity: Mono<ExampleEntity> = exampleEntityService.get(existingId)

        assertThat(
            exampleEntity
                .addMockBffContext()
                .block()
        )
            .isEqualTo(existingExampleEntity)
    }

    private fun <T> Mono<T>.addMockBffContext() =
        this.subscriberContext { ctx ->
            ctx.put(
                GRAPHQL_CONTEXT_KEY,
                BFFContext(
                    "example correlationId",
                    AppUser("applicationId")
                )
            )
        }
}
