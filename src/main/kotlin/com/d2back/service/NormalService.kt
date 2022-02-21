package com.d2back.service

import com.d2back.dto.NormalItemDto
import com.d2back.model.NormalItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface NormalService {

    fun findAll(specs: Specification<NormalItem>?, pageable: Pageable): Page<NormalItemDto>
    fun save(normalItemDto: NormalItemDto): NormalItemDto
}
