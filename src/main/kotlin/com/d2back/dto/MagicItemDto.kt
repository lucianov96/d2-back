package com.d2back.dto

abstract class MagicItemDto(
    open val id: Int,
    open val name: String,
    open val bonuses: List<BonusDto>
)
