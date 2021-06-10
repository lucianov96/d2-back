package com.d2back.repository.model

import com.d2back.repository.enum.BonusType

data class Bonus (
    val id: Int,
    val idItem: Int?,
    val idSet: Int?,
    val key: String,
    val objects: Int?,
    val bonusType: BonusType,
    val bonus1: ModifierBonus,
    val bonus2: ModifierBonus,
    val bonus3: ModifierBonus,
    val bonus4: ModifierBonus,
)
