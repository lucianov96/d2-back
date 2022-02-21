package com.d2back.mapper

import com.d2back.dto.ModifierBonusDto
import com.d2back.model.ModifierBonus
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
)
abstract class ModifierBonusMapper {

    fun toDto(modifierBonus: ModifierBonus?): ModifierBonusDto? {
        return if (modifierBonus != null) {
            ModifierBonusDto(
                modifierBonus.modifierType,
                modifierBonus.betweenValue1,
                modifierBonus.betweenValue2,
                modifierBonus.absoluteIntValue,
                modifierBonus.absoluteStringValue,
            )
        } else null
    }

    fun toModel(modifierBonusDto: ModifierBonusDto?): ModifierBonus? {
        return if (modifierBonusDto != null) {
            ModifierBonus().apply {
                modifierType = modifierBonusDto.modifierType
                betweenValue1 = modifierBonusDto.betweenValue1
                betweenValue2 = modifierBonusDto.betweenValue2
                absoluteIntValue = modifierBonusDto.absoluteIntValue
                absoluteStringValue = modifierBonusDto.absoluteStringValue
            }
        } else null
    }
}
