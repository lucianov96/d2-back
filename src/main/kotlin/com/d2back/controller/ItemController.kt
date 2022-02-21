package com.d2back.controller

import com.d2back.dto.NormalItemDto
import com.d2back.dto.UniqueItemDto
import com.d2back.model.NormalItem
import com.d2back.service.NormalItemService
import com.d2back.service.UniqueItemService
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
@RequestMapping("items")
@Validated
class ItemController(
        private val normalItemService: NormalItemService,
        private val uniqueItemService: UniqueItemService,
) {

    @GetMapping("/normal")
    fun getNormalItems(@SearchSpec specs: Specification<NormalItem>?, pageable: Pageable): Page<NormalItemDto> {
        return normalItemService.findAll(specs, pageable)
    }

    @PostMapping("/normal")
    fun createItem(@RequestBody normalItemDto: NormalItemDto): ResponseEntity<NormalItemDto> {
        return ResponseEntity.ok().body(
            normalItemService.save(normalItemDto)
        )
    }

    @PostMapping("/unique")
    fun createUniqueItem(@RequestBody uniqueItemDto: UniqueItemDto): ResponseEntity<UniqueItemDto> {
        return ResponseEntity.ok().body(
            uniqueItemService.save(uniqueItemDto)
        )
    }
}
