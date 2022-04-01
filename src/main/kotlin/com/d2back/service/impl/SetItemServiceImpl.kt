package com.d2back.service.impl

import com.d2back.dto.SetItemDto
import com.d2back.mapper.SetItemMapper
import com.d2back.model.SetItem
import com.d2back.model.enums.Key
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

    override fun findByNameLikeAndKeysIn(name: String?, keys: List<String>?, pageable: Pageable): Page<SetItemDto> {
        return if(name != null && keys != null) {
            setItemRepository.findAllByNameLikeAndBonuses_KeyIn("%${name}%", keys.map { Key.valueOf(it) }, pageable).map(setItemMapper::toDto)
        } else if(name == null && keys != null) {
            setItemRepository.findAllByBonuses_KeyIn(keys.map { Key.valueOf(it) }, pageable).map(setItemMapper::toDto)
        } else {
            setItemRepository.findAllByNameLike("%${name!!}%", pageable).map(setItemMapper::toDto)
        }
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
