package com.d2back.mapper

import com.d2back.dto.BonusDto
import com.d2back.dto.RunewordDto
import com.d2back.model.Runeword
import com.d2back.service.BonusService
import com.d2back.service.RunewordService
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
    private lateinit var bonusService: BonusService

    @Autowired
    private lateinit var bonusMapper: BonusMapper

    @Autowired
    private lateinit var runewordService: RunewordService

    fun toDto(runeword: Runeword): RunewordDto {
        return RunewordDto(
            runeword.id,
            runeword.name,
            runeword.level,
            runeword.bonuses.map {
                bonusMapper.toDto(it)
            },
            runeword.type,
            runeword.runes
        )
    }

    fun toModel(runewordDto: RunewordDto): Runeword {
        val bonusNumber = bonusService.getMaxNumber() +1
        val runewordNumber = runewordService.getMaxNumber() +1

        val bonusesDto = mutableListOf<BonusDto>()
        runewordDto.bonuses.forEachIndexed { index, element ->
            bonusesDto.add(element.copy(id = bonusNumber + index))
        }

        return Runeword().apply {
            id = runewordNumber
            name = runewordDto.name
            level = runewordDto.level
            type = runewordDto.type
            runes = runewordDto.runes
            bonuses = bonusesDto.map {
                bonusMapper.toModel(it, runewordId = runewordNumber)
            }

        }
    }
}
