package com.d2back.service.impl

import com.d2back.dto.SetDto
import com.d2back.mapper.SetMapper
import com.d2back.model.Set
import com.d2back.repository.SetRepository
import com.d2back.service.SetService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class SetServiceImpl(
    val setMapper: SetMapper,
    val setRepository: SetRepository
) : SetService {
    override fun findAll(specs: Specification<Set>?, pageable: Pageable): Page<SetDto> {
        return setRepository.findAll(Specification.where(specs), pageable).map(setMapper::toDto)
    }

    override fun save(setDto: SetDto): SetDto {
        val set = setRepository.save(
            setMapper.toModel(setDto)
        )
        return setMapper.toDto(set)
    }


}
