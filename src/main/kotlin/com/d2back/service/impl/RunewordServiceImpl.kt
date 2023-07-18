package com.d2back.service.impl

import com.d2back.dto.RunewordDto
import com.d2back.mapper.RunewordMapper
import com.d2back.model.enums.Key
import com.d2back.repository.RunewordRepository
import com.d2back.service.RunewordService
import com.d2back.model.enums.ItemType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RunewordServiceImpl(
    val runewordRepository: RunewordRepository,
    val runewordMapper: RunewordMapper
): RunewordService {

    override fun getMaxNumber(): Int =
        runewordRepository.getMaxNumber()

    override fun findByFilter(name: String?, keys: List<Key>?, type: ItemType?, runes: String?, pageable: Pageable): Page<RunewordDto> =
        runewordRepository.findByFilter(
            name = name,
            keys = keys,
            type = type,
            rune1 = runes?.split("-")?.getOrNull(0),
            rune2 = runes?.split("-")?.getOrNull(1),
            rune3 = runes?.split("-")?.getOrNull(2),
            rune4 = runes?.split("-")?.getOrNull(3),
            rune5 = runes?.split("-")?.getOrNull(4),
            rune6 = runes?.split("-")?.getOrNull(5),
            pageable = pageable
        ).map(runewordMapper::toDto)

    override fun save(runewordDto: RunewordDto): RunewordDto {
        val runeword = runewordRepository.save(
            runewordMapper.toModel(runewordDto)
        )
        return runewordMapper.toDto(runeword)
    }

}
