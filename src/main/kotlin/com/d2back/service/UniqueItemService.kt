package com.d2back.service

import com.d2back.dto.UniqueItemDto

interface UniqueItemService {

    fun save(uniqueItemDto: UniqueItemDto): UniqueItemDto
}
