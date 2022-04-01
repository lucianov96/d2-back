package com.d2back.service

import com.d2back.dto.RunewordDto
import com.d2back.model.Runeword
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface RunewordService {

    fun getMaxNumber(): Int
    fun findByNameLikeAndKeysIn(name: String?, keys: List<String>?, pageable: Pageable): Page<RunewordDto>
    fun findAll(specs: Specification<Runeword>?, pageable: Pageable): Page<RunewordDto>
    fun save(runewordDto: RunewordDto): RunewordDto
}
