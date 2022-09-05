package com.d2back.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(NOT_FOUND)
class NotFoundException(endpoint: String, status: HttpStatus = NOT_FOUND, message: String, cause: Throwable? = null) : ApiException(endpoint, status, message, cause)
