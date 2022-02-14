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

    abstract fun toDto(modifierBonus: ModifierBonus?): ModifierBonusDto?

}
