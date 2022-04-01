package com.d2back.mapper

import com.d2back.dto.ModifierBonusDto
import com.d2back.model.*
import com.d2back.model.Set
import com.d2back.model.enums.values.MagicItemValue
import com.d2back.model.enums.values.NormalItemValue
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

    fun toModel(modifierBonusDto: ModifierBonusDto?,
            normalItemValueDto: NormalItemValue? = null,
            magicItemValueDto: MagicItemValue? = null,
            bonusId: Int? = null,
            uniqueItemId: Int? = null,
            setItemId: Int? = null,
            runewordId: Int? = null,
            setId: Int? = null): ModifierBonus? {
        return if (modifierBonusDto != null) {

            if(listOfNotNull(bonusId, uniqueItemId, setItemId, runewordId, setId).size != 1) {
                // TODO: Throw Exception
            }

            ModifierBonus().apply {
                modifierType = modifierBonusDto.modifierType
                betweenValue1 = modifierBonusDto.betweenValue1
                betweenValue2 = modifierBonusDto.betweenValue2
                absoluteIntValue = modifierBonusDto.absoluteIntValue
                absoluteStringValue = modifierBonusDto.absoluteStringValue
                normalItemValue = normalItemValueDto
                magicItemValue = magicItemValueDto
                if(magicItemValueDto != null) {
                    bonus = Bonus().apply {
                        id = bonusId?: 0
                        uniqueItem = if(uniqueItemId != null) {
                            UniqueItem().apply {
                                id =  uniqueItemId
                            }
                        } else null
                        setItem = if(setItemId != null) {
                            SetItem().apply {
                                id =  setItemId
                            }
                        } else null
                        set = if(setId != null) {
                            Set().apply {
                                id =  setId
                            }
                        } else null
                        runeword = if(runewordId != null) {
                            Runeword().apply {
                                id =  runewordId
                            }
                        } else null
                    }
                }
            }
        } else null
    }
}
