package com.d2back.repository.model

data class Runeword(
    override val id: Int,
    override val name: String,
    val type: String,
    val runes: List<String>
): MagicItem(id, name)
