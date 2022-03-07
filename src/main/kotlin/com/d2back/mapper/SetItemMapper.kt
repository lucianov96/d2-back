package com.d2back.mapper

import com.d2back.dto.BonusDto
import com.d2back.dto.SetItemDto
import com.d2back.model.NormalItem
import com.d2back.model.SetItem
import com.d2back.model.Set
import com.d2back.service.BonusService
import com.d2back.service.SetItemService
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
abstract class SetItemMapper {

    @Autowired
    private lateinit var normalItemMapper: NormalItemMapper

    @Autowired
    private lateinit var setMapper: SetMapper

    @Autowired
    private lateinit var bonusMapper: BonusMapper

    @Autowired
    private lateinit var bonusService: BonusService

    @Autowired
    private lateinit var setItemService: SetItemService

    fun toDto(setItem: SetItem): SetItemDto {
        return SetItemDto(
            setItem.id,
            setItem.name,
            setItem.level,
            setItem.bonuses.map {
                bonusMapper.toDto(it)
            },
            normalItemMapper.toDto(setItem.normalItem),
            setMapper.toDto(setItem.set)

        )
    }

    fun toModel(setItemDto: SetItemDto): SetItem {

        val bonusNumber = bonusService.getMaxNumber() +1
        val setItemNumber = setItemService.getMaxNumber() +1

        val bonusesDto = mutableListOf<BonusDto>()
        setItemDto.bonuses.forEachIndexed { index, element ->
            bonusesDto.add(element.copy(id = bonusNumber + index))
        }

        val setItem = SetItem().apply {
            id = setItemNumber
            name = setItemDto.name
            level = setItemDto.level
            bonuses = bonusesDto.map {
                bonusMapper.toModel(it, setItemId = setItemNumber)
            }
            set = Set().apply {
                id = setItemDto.set.id!!
            }
            normalItem = NormalItem().apply {
                id = setItemDto.normalItem.id!!
            }
        }
        return setItem
    }
}
