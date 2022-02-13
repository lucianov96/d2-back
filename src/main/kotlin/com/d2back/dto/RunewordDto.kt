package com.d2back.dto

data class RunewordDto(
    override val id: Int,
    override val name: String,
    override val bonuses: List<BonusDto>,
    val type: String,
    val runes: List<String>
) : MagicItemDto(id, name, bonuses)
