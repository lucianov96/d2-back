package com.d2back.error

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

open class ApiException(val endpoint: String, val status: HttpStatus, message: String, cause: Throwable? = null) : RuntimeException(message, cause)
