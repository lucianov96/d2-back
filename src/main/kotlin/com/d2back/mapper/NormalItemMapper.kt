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

        val defense = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.defense }
        val damage1h = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.damage1h }
        val damage2h = normalItem.modifierBonuses.find { it.normalItemValue == NormalItemValue.damage2h }

        return NormalItemDto(
            normalItem.id,
            normalItem.name,
            normalItem.type,
            normalItem.level,
            normalItem.durability,
            normalItem.characterClass,
            normalItem.difficulty,
            modifierBonusMapper.toDto(defense),
            modifierBonusMapper.toDto(damage1h),
            modifierBonusMapper.toDto(damage2h),
        )
    }
}
