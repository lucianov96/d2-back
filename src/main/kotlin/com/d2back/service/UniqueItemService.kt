package com.d2back.service

import com.d2back.dto.UniqueItemDto
import com.d2back.model.UniqueItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface UniqueItemService {

    fun getMaxNumber(): Int
    fun findByNameLikeAndKeysIn(name: String?, keys: List<String>?, pageable: Pageable): Page<UniqueItemDto>
    fun findAll(specs: Specification<UniqueItem>?, pageable: Pageable): Page<UniqueItemDto>
    fun find(id: Int): UniqueItemDto
    fun save(uniqueItemDto: UniqueItemDto): UniqueItemDto
}
