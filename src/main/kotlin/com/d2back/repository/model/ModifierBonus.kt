package com.d2back.repository.model

import com.d2back.repository.enum.ModifierType

data class ModifierBonus(
    val modifierType: ModifierType,
    val betweenValue1: Int?,
    val betweenValue2: Int?,
    val absoluteIntValue: Int?,
    // This value is used only for skills
    val absoluteStringValue: String?,
)
