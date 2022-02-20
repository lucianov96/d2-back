package com.d2back.mapper

import com.d2back.dto.BonusDto
import com.d2back.model.Bonus
import com.d2back.model.ModifierBonus
import com.d2back.model.enum.ClassSkill
import com.d2back.model.enum.Key.aura_when_equipped
import com.d2back.model.enum.Key.charged_skill
import com.d2back.model.enum.Key.class_skill_level
import com.d2back.model.enum.Key.non_class_skill
import com.d2back.model.enum.Key.skill_levels
import com.d2back.model.enum.Key.skill_on_attack
import com.d2back.model.enum.Key.skill_on_death
import com.d2back.model.enum.Key.skill_on_hit
import com.d2back.model.enum.Key.skill_on_level_up
import com.d2back.model.enum.Key.skill_tab_levels
import com.d2back.model.enum.Key.skill_when_struck
import com.d2back.model.enum.ModifierType.absolute
import com.d2back.model.enum.Skill
import com.d2back.model.enum.TabSkill
import com.d2back.model.enum.values.MagicItemValue
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
abstract class BonusMapper {

    @Autowired
    private lateinit var modifierBonusMapper: ModifierBonusMapper

    fun toDto(bonus: Bonus): BonusDto {

        val bonus1 = bonus.modifierBonuses.find { it.magicItemValue == MagicItemValue.bonus1 }
        val bonus2 = bonus.modifierBonuses.find { it.magicItemValue == MagicItemValue.bonus2 }
        val bonus3 = bonus.modifierBonuses.find { it.magicItemValue == MagicItemValue.bonus3 }
        val bonus4 = bonus.modifierBonuses.find { it.magicItemValue == MagicItemValue.bonus4 }

        val bonuses = listOf(bonus1, bonus2, bonus3, bonus4).filter { it != null }

        return BonusDto(
            bonus.id,
            bonus.key,
            bonus.objects,
            bonus.bonusType,
            buildDescription(bonus, bonuses),
            modifierBonusMapper.toDto(bonus1),
            modifierBonusMapper.toDto(bonus2),
            modifierBonusMapper.toDto(bonus3),
            modifierBonusMapper.toDto(bonus4)
        )
    }

    private fun buildDescription(bonus: Bonus, modifierBonuses: List<ModifierBonus?>): String {
        var description = bonus.key.description

        when (bonus.key) {
            skill_on_attack, skill_on_death, skill_on_hit, skill_on_level_up, skill_when_struck -> {
                description.replaceFirst("&", "${modifierBonuses[0]?.absoluteIntValue}")
                description.replaceFirst("&", "${modifierBonuses[1]?.absoluteIntValue}")
                description.replaceFirst("&", "${Skill.valueOf(modifierBonuses[2]?.absoluteStringValue ?: "").tabDescription}")
            }
            aura_when_equipped, non_class_skill -> {
                description.replaceFirst("&", "${modifierBonuses[0]?.absoluteIntValue}")
                description.replaceFirst("&", "${Skill.valueOf(modifierBonuses[1]?.absoluteStringValue ?: "").tabDescription}")
            }
            charged_skill -> {
                description.replaceFirst("&", "${modifierBonuses[0]?.absoluteIntValue}")
                description.replaceFirst("&", "${Skill.valueOf(modifierBonuses[1]?.absoluteStringValue ?: "").tabDescription}")
                description.replaceFirst("&", "${modifierBonuses[2]?.absoluteIntValue}")
            }
            class_skill_level -> {
                description.replaceFirst("&", "${modifierBonuses[0]?.absoluteIntValue}")
                description.replaceFirst("&", "${ClassSkill.valueOf(modifierBonuses[1]?.absoluteStringValue ?: "").description}")
            }
            skill_levels -> {
                val skill = Skill.valueOf(modifierBonuses[1]?.absoluteStringValue ?: "")
                description.replaceFirst("&", "${modifierBonuses[0]?.absoluteIntValue}")
                description.replaceFirst("&", "${skill.tabDescription} ${skill.characterOnlyDescription}")
            }
            skill_tab_levels -> {
                description.replaceFirst("&", "${modifierBonuses[0]?.absoluteIntValue}")
                description.replaceFirst("&", "${TabSkill.valueOf(modifierBonuses[1]?.absoluteStringValue ?: "").description}")
            }
            else -> {
                modifierBonuses.forEach {
                    if (it?.modifierType == absolute) {
                        if (it.absoluteStringValue != null) {
                            description.replaceFirst("&", it.absoluteStringValue!!)
                        } else {
                            description.replaceFirst("&", "${it.absoluteIntValue!!}")
                        }
                    } else {
                        description.replaceFirst("&", "${it?.betweenValue1}-${it?.betweenValue2}")
                    }
                }
            }
        }

        return description
    }
}
