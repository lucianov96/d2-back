package com.d2back.repository.model

import com.d2back.repository.enum.CharacterClass
import com.d2back.repository.enum.Difficulty
import com.d2back.repository.enum.ItemType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "normal_items")
data class NormalItem(
    @Id val _id: String,
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
