package com.d2back.mapper

import com.d2back.dto.RunewordDto
import com.d2back.model.Runeword
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
abstract class RunewordMapper {

    @Autowired
    private lateinit var setMapper: SetMapper

    @Autowired
    private lateinit var bonusMapper: BonusMapper

    fun toDto(runeword: Runeword): RunewordDto {
        return RunewordDto(
            runeword.id,
            runeword.name,
            runeword.bonuses.map {
                bonusMapper.toDto(it)
            },
            runeword.type,
            runeword.runes
        )
    }

}
