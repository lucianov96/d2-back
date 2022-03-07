package com.d2back.controller

import com.d2back.dto.NormalItemDto
import com.d2back.dto.SetDto
import com.d2back.service.SetService
import com.sipios.springsearch.anotation.SearchSpec
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sets")
@Validated
class SetController(
    private val setService: SetService
) {

    @PostMapping
    fun createSet(@RequestBody setDto: SetDto): ResponseEntity<SetDto> {
        return ResponseEntity.ok().body(
            setService.save(setDto)
        )
    }

    @GetMapping
    fun index(@SearchSpec specs: Specification<com.d2back.model.Set>?, pageable: Pageable): Page<SetDto> {
        return setService.findAll(specs, pageable)
    }
}