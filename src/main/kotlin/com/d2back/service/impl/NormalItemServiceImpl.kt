package com.d2back.service.impl

import com.d2back.dto.NormalItemDto
import com.d2back.mapper.NormalItemMapper
import com.d2back.model.NormalItem
import com.d2back.repository.NormalItemRepository
import com.d2back.service.NormalService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class NormalItemServiceImpl(
    val normalItemMapper: NormalItemMapper,
    val normalItemRepository: NormalItemRepository
) : NormalService {

    override fun findAll(specs: Specification<NormalItem>?, pageable: Pageable): Page<NormalItemDto> {
        return normalItemRepository.findAll(Specification.where(specs), pageable).map(normalItemMapper::toDto)
    }
}
