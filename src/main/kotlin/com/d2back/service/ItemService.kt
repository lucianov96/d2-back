package com.d2back.service

import com.d2back.graphql.context.bffContext
import com.d2back.graphql.entity.ExampleEntity
import com.d2back.repository.dao.NormalItemsDAO
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class ItemService(
    val normalItemsDAO: NormalItemsDAO
) {

    fun getItemsByName(name: String): Mono<ExampleEntity> {
        return Mono.deferWithContext { reactiveCtx ->
            val bffContext = reactiveCtx.bffContext()
            val guantes = normalItemsDAO.findByName("Guantes pesados")
            ExampleEntity(bffContext.correlationId, "nudea").toMono()
        }
    }
}
