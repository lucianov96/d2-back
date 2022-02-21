package com.d2back.service.impl

import com.d2back.dto.UniqueItemDto
import com.d2back.mapper.UniqueItemMapper
import com.d2back.repository.UniqueItemRepository
import com.d2back.service.UniqueItemService
import org.springframework.stereotype.Service

@Service
class UniqueItemServiceImpl(
    val uniqueItemMapper: UniqueItemMapper,
    val uniqueItemRepository: UniqueItemRepository
) : UniqueItemService {

    override fun save(uniqueItemDto: UniqueItemDto): UniqueItemDto {
        val model = uniqueItemMapper.toModel(uniqueItemDto)
        val uniqueItem = uniqueItemRepository.save(
            model
        )
        return uniqueItemMapper.toDto(uniqueItem)
    }
}
