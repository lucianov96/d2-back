package com.d2back.mapper

import com.d2back.dto.BonusDto
import com.d2back.dto.UniqueItemDto
import com.d2back.model.NormalItem
import com.d2back.model.UniqueItem
import com.d2back.service.BonusService
import com.d2back.service.UniqueItemService
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
abstract class UniqueItemMapper {

    @Autowired
    private lateinit var normalItemMapper: NormalItemMapper

    @Autowired
    private lateinit var bonusMapper: BonusMapper

    @Autowired
    private lateinit var bonusService: BonusService

    @Autowired
    private lateinit var uniqueItemService: UniqueItemService

    fun toDto(uniqueItem: UniqueItem): UniqueItemDto {
        return UniqueItemDto(
            uniqueItem.id,
            uniqueItem.name,
            uniqueItem.level,
            uniqueItem.bonuses.map {
                bonusMapper.toDto(it)
            },
            normalItemMapper.toDto(uniqueItem.normalItem),
        )
    }

    fun toModel(uniqueItemDto: UniqueItemDto): UniqueItem {

        val bonusNumber = bonusService.getMaxNumber() +1
        val uniqueItemNumber = uniqueItemService.getMaxNumber() +1

        val bonusesDto = mutableListOf<BonusDto>()
        uniqueItemDto.bonuses.forEachIndexed { index, element ->
            bonusesDto.add(element.copy(id = bonusNumber + index))
        }

        val uniqueItem = UniqueItem().apply {
            id = uniqueItemNumber
            name = uniqueItemDto.name
            level = uniqueItemDto.level
            bonuses = bonusesDto.map {
                bonusMapper.toModel(it, uniqueItemId = uniqueItemNumber)
            }
            normalItem = NormalItem().apply {
                id = uniqueItemDto.normalItem.id!!
            }
        }

        return uniqueItem
    }
}
