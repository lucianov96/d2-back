package com.d2back.service.impl

import com.d2back.dto.UniqueItemDto
import com.d2back.mapper.UniqueItemMapper
import com.d2back.model.UniqueItem
import com.d2back.model.enums.Key
import com.d2back.repository.UniqueItemRepository
import com.d2back.service.UniqueItemService
import com.poketeammaker.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class UniqueItemServiceImpl(
    val uniqueItemMapper: UniqueItemMapper,
    val uniqueItemRepository: UniqueItemRepository
) : UniqueItemService {

    companion object {
        const val UNIQUE_ITEM_ENDPOINT = "/items/unique"
    }

    override fun getMaxNumber(): Int {
        return uniqueItemRepository.getMaxNumber()
    }

    override fun findByNameLikeAndKeysIn(name: String?, keys: List<String>?, pageable: Pageable): Page<UniqueItemDto> {
        return if(name != null && keys != null) {
            uniqueItemRepository.findAllByNameLikeAndBonuses_KeyIn("%${name}%", keys.map { Key.valueOf(it) }, pageable).map(uniqueItemMapper::toDto)
        } else if(name == null && keys != null) {
            uniqueItemRepository.findAllByBonuses_KeyIn(keys.map { Key.valueOf(it) }, pageable).map(uniqueItemMapper::toDto)
        } else {
            uniqueItemRepository.findAllByNameLike("%${name!!}%", pageable).map(uniqueItemMapper::toDto)
        }
    }

    override fun findAll(specs: Specification<UniqueItem>?, pageable: Pageable): Page<UniqueItemDto> {
        return uniqueItemRepository.findAll(Specification.where(specs), pageable).map(uniqueItemMapper::toDto)
    }

    override fun find(id: Int): UniqueItemDto {
        return uniqueItemMapper.toDto(
            uniqueItemRepository.findById(id).orElseThrow {
                NotFoundException(
                    endpoint = UNIQUE_ITEM_ENDPOINT,
                    message = "Unique item not found"
                )
            }
        )
    }

    override fun save(uniqueItemDto: UniqueItemDto): UniqueItemDto {
        val model = uniqueItemMapper.toModel(uniqueItemDto)
        val uniqueItem = uniqueItemRepository.save(
            model
        )
        return uniqueItemMapper.toDto(uniqueItem)
    }
}
