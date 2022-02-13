package com.d2back.model

import com.d2back.model.enum.ModifierType

data class ModifierBonus(
    val modifierType: ModifierType,
    val betweenValue1: Int?,
    val betweenValue2: Int?,
    val absoluteIntValue: Int?,
    // This value is used only for skills
    val absoluteStringValue: String?,
)
