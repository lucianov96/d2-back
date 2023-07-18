package com.d2back.model.request

import com.d2back.model.enums.ItemType

data class ItemFilterRequest (
    val name: String?,
    val keys: String?,
    val set: String?,
    val runes: String?,
    val quality: Quality?,
    val type: ItemType?
) {
    enum class Quality {
        SET, UNIQUE, RUNEWORD
    }
}
