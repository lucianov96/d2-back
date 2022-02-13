package com.d2back.dto

data class SetItemDto(
    override val id: Int,
    override val name: String,
    override val bonuses: List<BonusDto>,
    val normalItem: NormalItemDto,
    val set: SetDto
) : MagicItemDto(id, name, bonuses)
