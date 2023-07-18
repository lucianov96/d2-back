package com.d2back.controller.validator

import com.d2back.error.BadRequestException
import com.d2back.model.enums.Key
import com.d2back.model.request.ItemFilterRequest
import com.d2back.model.request.ItemFilterRequest.Quality.RUNEWORD
import com.d2back.model.request.ItemFilterRequest.Quality.SET
import org.springframework.stereotype.Component

@Component
class ItemFilterRequestValidator {

    val itemsEndpoint = "/items"

    fun validateRequest(request: ItemFilterRequest) {
        if (!request.runes.isNullOrEmpty() && request.quality != RUNEWORD) {
            throw BadRequestException(
                endpoint = itemsEndpoint,
                message = "Runewords can only be with runeword quality"
            )
        } else if (!request.set.isNullOrEmpty() && request.quality != SET) {
            throw BadRequestException(
                endpoint = itemsEndpoint,
                message = "Set can only be with set quality"
            )
        }
        try {
            request.keys?.split(",")?.map {
                Key.valueOf(it)
            }
        } catch (e: Exception) {
            throw BadRequestException(
                endpoint = itemsEndpoint,
                message = e.message?: "",
                cause = e
            )
        }
    }
}
