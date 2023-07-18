package com.d2back.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(BAD_REQUEST)
class BadRequestException(endpoint: String, status: HttpStatus = BAD_REQUEST, message: String, cause: Throwable? = null) : ApiException(endpoint, status, message, cause)
