package com.d2back.repository.model

data class ModifierBonus (
    val modifierType: String,
    val betweenValue1: Int?,
    val betweenValue2: Int?,
    val absoluteValue: Int?,
)
