package com.d2back.mapper

import com.d2back.dto.BonusDto
import com.d2back.dto.SetDto
import com.d2back.service.BonusService
import com.d2back.service.SetService
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

    @Autowired
    private lateinit var bonusService: BonusService

    @Autowired
    private lateinit var setService: SetService

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
        val number = bonusService.getMaxNumber() +1
        val setNumber = setService.getMaxNumber() +1

        val bonusesDto = mutableListOf<BonusDto>()
        setDto.bonuses.forEachIndexed { index, element ->
            bonusesDto.add(element.copy(id = number + index))
        }

        return com.d2back.model.Set().apply {
            id = setNumber
            name = setDto.name
            bonuses = bonusesDto.map {
                bonusMapper.toModel(it, setId = setNumber)
            }
        }
    }
}
