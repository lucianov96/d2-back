package com.d2back.service.impl

import com.d2back.dto.SetItemDto
import com.d2back.mapper.SetItemMapper
import com.d2back.model.SetItem
import com.d2back.repository.SetItemRepository
import com.d2back.service.SetItemService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class SetItemServiceImpl(
    val setItemRepository: SetItemRepository,
    val setItemMapper: SetItemMapper
): SetItemService {
    override fun getMaxNumber(): Int {
        return setItemRepository.getMaxNumber()
    }

    override fun findAll(specs: Specification<SetItem>?, pageable: Pageable): Page<SetItemDto> {
        return setItemRepository.findAll(Specification.where(specs), pageable).map(setItemMapper::toDto)
    }

    override fun save(setItemDto: SetItemDto): SetItemDto {
        val setItem = setItemRepository.save(
            setItemMapper.toModel(setItemDto)
        )
        return setItemMapper.toDto(setItem)
    }
}
