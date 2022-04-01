package com.d2back.controller

import com.d2back.dto.*
import com.d2back.model.NormalItem
import com.d2back.model.Runeword
import com.d2back.model.SetItem
import com.d2back.model.UniqueItem
import com.d2back.request.ItemFilterRequest
import com.d2back.service.NormalItemService
import com.d2back.service.RunewordService
import com.d2back.service.SetItemService
import com.d2back.service.UniqueItemService
import com.sipios.springsearch.anotation.SearchSpec
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("items")
@Validated
class ItemController(
    private val normalItemService: NormalItemService,
    private val uniqueItemService: UniqueItemService,
    private val setItemService: SetItemService,
    private val runewordService: RunewordService,
) {

    @GetMapping("/normal")
    fun indexNormalItems(@SearchSpec specs: Specification<NormalItem>?, pageable: Pageable): Page<NormalItemDto> {
        return normalItemService.findAll(specs, pageable)
    }

    @GetMapping("/unique")
    fun indexUniqueItems(@SearchSpec specs: Specification<UniqueItem>?, pageable: Pageable): Page<UniqueItemDto> {
        return uniqueItemService.findAll(specs, pageable)
    }

    @GetMapping("/set")
    fun indexSetItems(@SearchSpec specs: Specification<SetItem>?, pageable: Pageable): Page<SetItemDto> {
        return setItemService.findAll(specs, pageable)
    }

    @GetMapping("/runeword")
    fun indexRunewords(@SearchSpec specs: Specification<Runeword>?, pageable: Pageable): Page<RunewordDto> {
        return runewordService.findAll(specs, pageable)
    }

    @PostMapping("/normal")
    fun createNormalItem(@RequestBody normalItemDto: NormalItemDto): ResponseEntity<NormalItemDto> {
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

    @PostMapping("/set")
    fun createSetItem(@RequestBody setItemDto: SetItemDto): ResponseEntity<SetItemDto> {
        return ResponseEntity.ok().body(
            setItemService.save(setItemDto)
        )
    }

    @PostMapping("/runeword")
    fun createRuneword(@RequestBody runewordDto: RunewordDto): ResponseEntity<RunewordDto> {
        return ResponseEntity.ok().body(
            runewordService.save(runewordDto)
        )
    }

    @GetMapping
    fun index(@Valid request: ItemFilterRequest, pageable: Pageable): Page<MagicItemDto> {

        val pageableUniqueItems = PageRequest.of(pageable.pageNumber, pageable.pageSize/3)
        val pageableSetItems = PageRequest.of(pageable.pageNumber, pageable.pageSize/3)
        val pageableRunewords = PageRequest.of(pageable.pageNumber, pageable.pageSize/3)

        val pageUniqueItems = uniqueItemService.findByNameLikeAndKeysIn(request.name, request.keys?.split(","), pageableUniqueItems)
        val pageSetItems = setItemService.findByNameLikeAndKeysIn(request.name, request.keys?.split(","), pageableSetItems)
        val pageRunewords = runewordService.findByNameLikeAndKeysIn(request.name, request.keys?.split(","), pageableRunewords)

        val magicItems = mutableListOf<MagicItemDto>()
        magicItems.addAll(
            pageUniqueItems.content
        )
        magicItems.addAll(
            pageSetItems.content
        )
        magicItems.addAll(
            pageRunewords.content
        )
        return PageImpl(magicItems)
    }
}
