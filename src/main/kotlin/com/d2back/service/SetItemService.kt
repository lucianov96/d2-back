package com.d2back.service

import com.d2back.dto.SetItemDto
import com.d2back.model.SetItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface SetItemService {

    fun getMaxNumber(): Int
    fun findByNameLikeAndKeysIn(name: String?, keys: List<String>?, pageable: Pageable): Page<SetItemDto>
    fun findAll(specs: Specification<SetItem>?, pageable: Pageable): Page<SetItemDto>
    fun find(id: Int): SetItemDto
    fun save(setItemDto: SetItemDto): SetItemDto
}
