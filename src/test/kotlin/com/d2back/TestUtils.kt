package com.d2back

import com.d2back.model.ModifierBonus
import com.d2back.model.NormalItem
import com.d2back.model.SetItem
import com.d2back.model.UniqueItem
import com.d2back.model.enums.CharacterClass
import com.d2back.model.enums.Difficulty
import com.d2back.model.enums.ItemType

const val ID_ITEM = 1

fun aNormalItem(
    id: Int = 0,
    name: String = "",
    type: ItemType = ItemType.crossbow,
    level: Int = 0,
    strength: Int? = null,
    dextrerity: Int? = null,
    durability: Int? = null,
    sockets: Int? = null,
    characterClass: CharacterClass? = null,
    difficulty: Difficulty = Difficulty.normal,
    setItem: SetItem? = null,
    uniqueItem: UniqueItem? = null,
    modifierBonuses: List<ModifierBonus> = mutableListOf()
) =
    NormalItem().apply {
        this.id = id
        this.name = name
        this.type = type
        this.level = level
        this.strength = strength
        this.dextrerity = dextrerity
        this.durability = durability
        this.sockets = sockets
        this.characterClass = characterClass
        this.difficulty = difficulty
        this.setItem = setItem
        this.uniqueItem = uniqueItem
        this.modifierBonuses = modifierBonuses
    }
