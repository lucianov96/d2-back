package com.d2back.dto

import com.d2back.model.enum.CharacterClass
import com.d2back.model.enum.Difficulty
import com.d2back.model.enum.ItemType

data class NormalItemDto(
    val id: Int,
    val name: String,
    val type: ItemType,
    val level: Int,
    val durability: Int,
    val characterClass: CharacterClass?,
    val difficulty: Difficulty,
    val defenseMin: ModifierBonusDto?,
    val defenseMax: ModifierBonusDto?,
    val damage1hMin: ModifierBonusDto?,
    val damage2hMin: ModifierBonusDto?,
    val damage1hMax: ModifierBonusDto?,
    val damage2hMax: ModifierBonusDto?
)
