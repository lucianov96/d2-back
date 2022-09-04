package com.d2back.error

import com.poketeammaker.exception.ApiError
import com.poketeammaker.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(value = [(NotFoundException::class)])
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ApiError> {
        val error = ApiError(
            UUID.randomUUID(),
            e.endpoint,
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.name,
            e.message ?: ""
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

}
