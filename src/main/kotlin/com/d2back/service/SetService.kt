package com.d2back.service

import com.d2back.dto.SetDto
import com.d2back.model.Set
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface SetService {

    fun getMaxNumber(): Int
    fun findAll(specs: Specification<Set>?, pageable: Pageable): Page<SetDto>
    fun save(setDto: SetDto): SetDto
}
