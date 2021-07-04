package com.d2back.repository.model

data class SetItem(
    override val id: Int,
    override val name: String,
    val idNormalItem: Int,
    val idSet: Int
) : MagicItem(id, name)
