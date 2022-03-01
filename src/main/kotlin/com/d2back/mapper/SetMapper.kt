package com.d2back.mapper

import com.d2back.dto.SetDto
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
abstract class SetMapper {

    @Autowired
    private lateinit var bonusMapper: BonusMapper

    fun toDto(set: com.d2back.model.Set): SetDto {
        return SetDto(
            set.id,
            set.name,
            set.bonuses.map {
                bonusMapper.toDto(it)
            }
        )
    }

    fun toModel(setDto: SetDto): com.d2back.model.Set {
        return com.d2back.model.Set().apply {
            id = setDto.id
            name = setDto.name
            bonuses = setDto.bonuses.map {
                bonusMapper.toModel(it, setId = setDto.id)
            }
        }
    }
}
