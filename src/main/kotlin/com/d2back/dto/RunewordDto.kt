package com.d2back.dto

import com.d2back.model.enum.ItemType

data class RunewordDto(
    override val id: Int,
    override val name: String,
    override val bonuses: List<BonusDto>,
    val type: ItemType,
    val runes: List<String>
) : MagicItemDto(id, name, bonuses)
