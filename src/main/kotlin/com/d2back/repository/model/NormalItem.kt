package com.d2back.repository.model

data class NormalItem (
        val id: Int,
        val name: String,
        val type: String,
        val level: Int,
        val defense: Int?,
        val durability: Int,
        val characterClass: String,
        val difficulty: String,
        val damage1hMin: ModifierBonus?,
        val damage2hMin: ModifierBonus?,
        val damage1hMax: ModifierBonus?,
        val damage2hMax: ModifierBonus?
)
