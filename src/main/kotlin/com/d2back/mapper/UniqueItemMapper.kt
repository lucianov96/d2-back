package com.d2back.mapper

import com.d2back.dto.UniqueItemDto
import com.d2back.model.UniqueItem
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

    fun toDto(uniqueItem: UniqueItem): UniqueItemDto {
        return UniqueItemDto(
            uniqueItem.id,
            uniqueItem.name,
            uniqueItem.bonuses.map {
                bonusMapper.toDto(it)
            },
            normalItemMapper.toDto(uniqueItem.normalItem),

        )
    }
}
