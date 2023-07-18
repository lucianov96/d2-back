package com.d2back.controller

import com.d2back.controller.validator.ItemFilterRequestValidator
import com.d2back.dto.*
import com.d2back.model.enums.Key
import com.d2back.model.request.ItemFilterRequest
import com.d2back.model.request.ItemFilterRequest.Quality.*
import com.d2back.service.NormalItemService
import com.d2back.service.RunewordService
import com.d2back.service.SetItemService
import com.d2back.service.UniqueItemService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
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
    private val validator: ItemFilterRequestValidator
) {

    @PostMapping("/normal")
    fun createNormalItem(@RequestBody normalItemDto: NormalItemDto): ResponseEntity<NormalItemDto> {
        return ResponseEntity(
            normalItemService.save(normalItemDto),
            HttpStatus.CREATED
        )
    }

    @PostMapping("/unique")
    fun createUniqueItem(@RequestBody uniqueItemDto: UniqueItemDto): ResponseEntity<UniqueItemDto> {
        return ResponseEntity(
            uniqueItemService.save(uniqueItemDto),
            HttpStatus.CREATED
        )
    }

    @PostMapping("/set")
    fun createSetItem(@RequestBody setItemDto: SetItemDto): ResponseEntity<SetItemDto> {
        return ResponseEntity(
            setItemService.save(setItemDto),
            HttpStatus.CREATED
        )
    }

    @PostMapping("/runeword")
    fun createRuneword(@RequestBody runewordDto: RunewordDto): ResponseEntity<RunewordDto> {
        return ResponseEntity(
            runewordService.save(runewordDto),
            HttpStatus.CREATED
        )
    }

    @GetMapping
    fun index(@Valid request: ItemFilterRequest, pageable: Pageable): Page<MagicItemDto> {
        validator.validateRequest(request)

        val pageableUniqueItems = PageRequest.of(pageable.pageNumber, pageable.pageSize/3)
        val pageableSetItems = PageRequest.of(pageable.pageNumber, pageable.pageSize/3)
        val pageableRunewords = PageRequest.of(pageable.pageNumber, pageable.pageSize/3)

        val magicItems = mutableListOf<MagicItemDto>()
        if(request.quality == null || request.quality == UNIQUE) {
            val pageUniqueItems = uniqueItemService.findByFilter(
                request.name,
                request.keys?.split(",")?.map {
                    Key.valueOf(it)
                },
                request.type,
                pageableUniqueItems
            )
            magicItems.addAll(
                pageUniqueItems.content
            )
        }
        if(request.quality == null || request.quality == SET) {
            val pageSetItems = setItemService.findByFilter(
                request.name,
                request.keys?.split(",")?.map {
                    Key.valueOf(it)
                },
                request.type,
                request.set,
                pageableSetItems
            )
            magicItems.addAll(
                pageSetItems.content
            )
        }
        if(request.quality == null || request.quality == RUNEWORD) {
            val pageRunewords = runewordService.findByFilter(
                request.name,
                request.keys?.split(",")?.map {
                    Key.valueOf(it)
                },
                request.type,
                request.runes,
                pageableRunewords
            )
            magicItems.addAll(
                pageRunewords.content
            )
        }
        return PageImpl(magicItems)
    }
}
