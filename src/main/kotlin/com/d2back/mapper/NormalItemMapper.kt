package com.d2back.mapper

import com.d2back.dto.NormalItemDto
import com.d2back.model.NormalItem
import com.d2back.model.enum.values.NormalItemValue
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy
import org.springframework.beans.factory.annotation.Autowired

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
)
abstract class NormalItemMapper {

    @Autowired
    private lateinit var modifierBonusMapper: ModifierBonusMapper

    fun toDto(normalItem: NormalItem): NormalItemDto {

        val defenseMin = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.defenseMin }
        val defenseMax = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.defenseMax }
        val damage1hMin = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.damage1hMin }
        val damage1hMax = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.damage1hMax }
        val damage2hMin = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.damage2hMin }
        val damage2hMax = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.damage2hMax }

        return NormalItemDto(
            normalItem.id,
            normalItem.name,
            normalItem.type,
            normalItem.level,
            normalItem.durability,
            normalItem.characterClass,
            normalItem.difficulty,
            modifierBonusMapper.toDto(defenseMin),
            modifierBonusMapper.toDto(defenseMax),
            modifierBonusMapper.toDto(damage1hMin),
            modifierBonusMapper.toDto(damage1hMax),
            modifierBonusMapper.toDto(damage2hMin),
            modifierBonusMapper.toDto(damage2hMax),
        )
    }

}
