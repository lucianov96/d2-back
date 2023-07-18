package com.d2back

import com.d2back.model.*
import com.d2back.model.enums.CharacterClass
import com.d2back.model.enums.Difficulty
import com.d2back.model.enums.ItemType
import com.d2back.model.enums.ModifierType
import com.d2back.model.enums.values.MagicItemValue
import com.d2back.model.enums.values.NormalItemValue
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets

const val ID_ITEM = 1

class TestUtils {
    companion object {
        fun getFile(fileDirectory: String): String {
            val stream = this::class.java.classLoader.getResourceAsStream(fileDirectory)
            return StreamUtils.copyToString(stream, StandardCharsets.UTF_8).trimIndent()
        }
        fun <T> formatJson(objectMapper: ObjectMapper, json: String, type: Class<T>): String {
            val entity = objectMapper.readValue(json, type)
            val formattedJson = objectMapper.writeValueAsString(entity)
            return formattedJson.replace("\\", "")
        }
    }
}

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

fun aModifierBounus(
    id: Int = 0,
    modifierType: ModifierType = ModifierType.absolute,
    betweenValue1: Int? = null,
    betweenValue2: Int? = null,
    absoluteIntValue: Int? = null,
    normalItemValue: NormalItemValue? = null,
    magicItemValue: MagicItemValue? = null,
    bonus: Bonus? = null,
    normalItem: NormalItem? = null,
) = ModifierBonus().apply {
    this.id = id
    this.modifierType = modifierType
    this.betweenValue1 = betweenValue1
    this.betweenValue2 = betweenValue2
    this.absoluteIntValue = absoluteIntValue
    this.normalItemValue = normalItemValue
    this.magicItemValue = magicItemValue
    this.bonus = bonus
    this.normalItem = normalItem
}
