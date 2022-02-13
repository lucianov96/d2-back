package com.d2back.dto

data class UniqueItemDto(
    override val id: Int,
    override val name: String,
    override val bonuses: List<BonusDto>,
    val normalItem: NormalItemDto
) : MagicItemDto(id, name, bonuses)
