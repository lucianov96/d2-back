package com.d2back.service

import com.d2back.dto.SetItemDto
import com.d2back.model.enums.ItemType
import com.d2back.model.enums.Key
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SetItemService {

    fun getMaxNumber(): Int
    fun findByFilter(name: String?, keys: List<Key>?, type: ItemType?, set: String?, pageable: Pageable): Page<SetItemDto>
    fun save(setItemDto: SetItemDto): SetItemDto
}
