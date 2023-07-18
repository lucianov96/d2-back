package com.d2back.service

import com.d2back.dto.UniqueItemDto
import com.d2back.model.enums.ItemType
import com.d2back.model.enums.Key
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UniqueItemService {

    fun getMaxNumber(): Int
    fun findByFilter(name: String?, keys: List<Key>?, type: ItemType?, pageable: Pageable): Page<UniqueItemDto>
    fun save(uniqueItemDto: UniqueItemDto): UniqueItemDto
}
