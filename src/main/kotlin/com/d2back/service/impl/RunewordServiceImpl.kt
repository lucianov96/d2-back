package com.d2back.service.impl

import com.d2back.dto.RunewordDto
import com.d2back.mapper.RunewordMapper
import com.d2back.model.Runeword
import com.d2back.model.enums.Key
import com.d2back.repository.RunewordRepository
import com.d2back.service.RunewordService
import com.d2back.error.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class RunewordServiceImpl(
    val runewordRepository: RunewordRepository,
    val runewordMapper: RunewordMapper
): RunewordService {

    companion object {
        const val RUNEWORD_ITEM_ENDPOINT = "/items/runeword"
    }

    override fun getMaxNumber(): Int {
        return runewordRepository.getMaxNumber()
    }

    override fun findByNameLikeAndKeysIn(name: String?, keys: List<String>?, pageable: Pageable): Page<RunewordDto> {
        return if(name != null && keys != null) {
            runewordRepository.findAllByNameLikeAndBonuses_KeyIn("%${name}%", keys.map { Key.valueOf(it) }, pageable).map(runewordMapper::toDto)
        } else if(name == null && keys != null) {
            runewordRepository.findAllByBonuses_KeyIn(keys.map { Key.valueOf(it) }, pageable).map(runewordMapper::toDto)
        } else {
            runewordRepository.findAllByNameLike("%${name!!}%", pageable).map(runewordMapper::toDto)
        }
    }

    override fun findAll(specs: Specification<Runeword>?, pageable: Pageable): Page<RunewordDto> {
        return runewordRepository.findAll(specs, pageable).map(runewordMapper::toDto)
    }

    override fun find(id: Int): RunewordDto {
        return runewordMapper.toDto(
            runewordRepository.findById(id).orElseThrow {
                NotFoundException(
                    endpoint = RUNEWORD_ITEM_ENDPOINT,
                    message = "Runeword not found"
                )
            }
        )
    }

    override fun save(runewordDto: RunewordDto): RunewordDto {
        val runeword = runewordRepository.save(
            runewordMapper.toModel(runewordDto)
        )
        return runewordMapper.toDto(runeword)
    }

}
