package com.d2back.dto

import com.d2back.model.enums.ItemType

data class RunewordDto(
    override val id: Int,
    override val name: String,
    override val level: Int,
    override val bonuses: List<BonusDto>,
    val type: ItemType,
    val runes: String // separated by "-"
) : MagicItemDto(id, name, level, bonuses)
