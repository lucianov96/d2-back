package com.d2back.service.impl

import com.d2back.dto.UniqueItemDto
import com.d2back.mapper.UniqueItemMapper
import com.d2back.model.UniqueItem
import com.d2back.repository.UniqueItemRepository
import com.d2back.service.UniqueItemService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class UniqueItemServiceImpl(
    val uniqueItemMapper: UniqueItemMapper,
    val uniqueItemRepository: UniqueItemRepository
) : UniqueItemService {
    override fun getMaxNumber(): Int {
        return uniqueItemRepository.getMaxNumber()
    }

    override fun findAll(specs: Specification<UniqueItem>?, pageable: Pageable): Page<UniqueItemDto> {
        return uniqueItemRepository.findAll(Specification.where(specs), pageable).map(uniqueItemMapper::toDto)
    }

    override fun save(uniqueItemDto: UniqueItemDto): UniqueItemDto {
        val model = uniqueItemMapper.toModel(uniqueItemDto)
        val uniqueItem = uniqueItemRepository.save(
            model
        )
        return uniqueItemMapper.toDto(uniqueItem)
    }
}
