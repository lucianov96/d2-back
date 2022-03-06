package com.d2back.service.impl

import com.d2back.dto.RunewordDto
import com.d2back.mapper.RunewordMapper
import com.d2back.model.Runeword
import com.d2back.repository.RunewordRepository
import com.d2back.service.RunewordService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class RunewordServiceImpl(
    val runewordRepository: RunewordRepository,
    val runewordMapper: RunewordMapper
): RunewordService {
    override fun getMaxNumber(): Int {
        return runewordRepository.getMaxNumber()
    }

    override fun findAll(specs: Specification<Runeword>?, pageable: Pageable): Page<RunewordDto> {
        return runewordRepository.findAll(specs, pageable).map(runewordMapper::toDto)
    }

    override fun save(runewordDto: RunewordDto): RunewordDto {
        val runeword = runewordRepository.save(
            runewordMapper.toModel(runewordDto)
        )
        return runewordMapper.toDto(runeword)
    }

}
