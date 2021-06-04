package com.d2back.repository.model

data class UniqueItem(
    override val id: Int,
    override val name: String,
    val idNormalItem: Int
): MagicItem(id, name)
