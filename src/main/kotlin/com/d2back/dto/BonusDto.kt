package com.d2back.dto

import com.d2back.model.enum.BonusType
import com.d2back.model.enum.Key

data class BonusDto(
    val id: Int,
    val key: Key,
    val objects: Int?,
    val bonusType: BonusType,
    val description: String,
    val bonus1: ModifierBonusDto?,
    val bonus2: ModifierBonusDto?,
    val bonus3: ModifierBonusDto?,
    val bonus4: ModifierBonusDto?,
)
