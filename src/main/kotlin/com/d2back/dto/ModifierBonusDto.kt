package com.d2back.dto

import com.d2back.model.enums.ModifierType

data class ModifierBonusDto(
    val modifierType: ModifierType,
    val betweenValue1: Int?,
    val betweenValue2: Int?,
    val absoluteIntValue: Int?,
    // This value is used only for skills
    val absoluteStringValue: String?,
)
