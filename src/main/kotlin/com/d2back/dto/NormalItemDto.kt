package com.d2back.dto

import com.d2back.model.enums.CharacterClass
import com.d2back.model.enums.Difficulty
import com.d2back.model.enums.ItemType

data class NormalItemDto(
    val id: Int?,
    val name: String,
    val type: ItemType,
    val level: Int,
    val strength: Int?,
    val dextrerity: Int?,
    val durability: Int?,
    val sockets: Int?,
    val characterClass: CharacterClass?,
    val difficulty: Difficulty,
    val defense: ModifierBonusDto?,
    val damage1h: ModifierBonusDto?,
    val damage2h: ModifierBonusDto?,
)
