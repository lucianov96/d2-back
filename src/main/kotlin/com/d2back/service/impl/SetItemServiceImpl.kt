package com.d2back.service.impl

import com.d2back.dto.SetItemDto
import com.d2back.mapper.SetItemMapper
import com.d2back.model.enums.Key
import com.d2back.repository.SetItemRepository
import com.d2back.service.SetItemService
import com.d2back.model.enums.ItemType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SetItemServiceImpl(
    val setItemRepository: SetItemRepository,
    val setItemMapper: SetItemMapper
): SetItemService {

    override fun getMaxNumber(): Int = setItemRepository.getMaxNumber()

    override fun findByFilter(name: String?, keys: List<Key>?, type: ItemType?, set: String?, pageable: Pageable): Page<SetItemDto> =
        setItemRepository.findByFilter(
            name, keys, type = type, set = set, pageable = pageable).map(setItemMapper::toDto)

    override fun save(setItemDto: SetItemDto): SetItemDto {
        val setItem = setItemRepository.save(
            setItemMapper.toModel(setItemDto)
        )
        return setItemMapper.toDto(setItem)
    }
}
