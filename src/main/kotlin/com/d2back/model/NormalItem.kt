package com.d2back.model

import com.d2back.model.enum.CharacterClass
import com.d2back.model.enum.Difficulty
import com.d2back.model.enum.ItemType

data class NormalItem(
    val id: Int,
    val name: String,
    val type: ItemType,
    val level: Int,
    val defenseMin: Int?,
    val defenseMax: Int?,
    val durability: Int,
    val characterClass: CharacterClass?,
    val difficulty: Difficulty,
    val damage1hMin: ModifierBonus?,
    val damage2hMin: ModifierBonus?,
    val damage1hMax: ModifierBonus?,
    val damage2hMax: ModifierBonus?
)
