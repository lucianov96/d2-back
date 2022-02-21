package com.d2back.dto

data class SetItemDto(
    override val id: Int,
    override val name: String,
    override val level: Int,
    override val bonuses: List<BonusDto>,
    val normalItem: NormalItemDto,
    val set: SetDto
) : MagicItemDto(id, name, level, bonuses)
