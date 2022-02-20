package com.d2back.mapper

import com.d2back.dto.SetItemDto
import com.d2back.model.SetItem
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

    fun toDto(setItem: SetItem): SetItemDto {
        return SetItemDto(
            setItem.id,
            setItem.name,
            setItem.bonuses.map {
                bonusMapper.toDto(it)
            },
            normalItemMapper.toDto(setItem.normalItem),
            setMapper.toDto(setItem.set)

        )
    }
}
