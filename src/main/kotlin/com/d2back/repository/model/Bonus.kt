package com.d2back.repository.model

data class Bonus (
    val id: Int,
    val idItem: Int?,
    val idSet: Int?,
    val key: String,
    val objects: Int?,
    val modifierType: String,
    val bonus1: Bonus,
    val bonus2: Bonus,
    val bonus3: Bonus,
    val bonus4: Bonus,
)
