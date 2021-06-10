package com.d2back.repository.model

import com.d2back.repository.enum.CharacterClass
import com.d2back.repository.enum.Difficulty
import com.d2back.repository.enum.ItemType

data class NormalItem (
    val id: Int,
    val name: String,
    val type: ItemType,
    val level: Int,
    val defense: Int?,
    val durability: Int,
    val characterClass: CharacterClass?,
    val difficulty: Difficulty,
    val damage1hMin: ModifierBonus?,
    val damage2hMin: ModifierBonus?,
    val damage1hMax: ModifierBonus?,
    val damage2hMax: ModifierBonus?
)
