package com.poketeammaker.exception

import java.util.UUID

data class ApiError(
    val requestId: UUID,
    val endpoint: String,
    val code: Int,
    val status: String,
    val message: String,
)
