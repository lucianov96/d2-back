package com.d2back.dto

data class SetDto(
    val id: Int,
    val name: String,
    val bonuses: List<BonusDto>
)
