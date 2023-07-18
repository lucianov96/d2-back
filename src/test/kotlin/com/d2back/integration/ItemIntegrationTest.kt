package com.d2back.integration

import com.d2back.ID_ITEM
import com.d2back.TestUtils.Companion.formatJson
import com.d2back.TestUtils.Companion.getFile
import com.d2back.aModifierBounus
import com.d2back.aNormalItem
import com.d2back.controller.ItemController
import com.d2back.model.ModifierBonus
import com.d2back.model.enums.Difficulty
import com.d2back.model.enums.ItemType
import com.d2back.model.enums.ModifierType
import com.d2back.model.enums.values.NormalItemValue
import com.d2back.repository.NormalItemRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.Optional

@SpringBootTest
@AutoConfigureMockMvc
class ItemIntegrationTest {

    @MockkBean
    lateinit var normalItemRepository: NormalItemRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    val aNormalItemJson = getFile("normal-item.json")
    val aBadNormalItemJson = aNormalItemJson.replace("armor", "hamburger")

    val aNormalItem = aNormalItem(
        id = ID_ITEM,
        name = "Armadura Acolchada",
    )

    @Test
    fun createNormalItemOk() {

        every { normalItemRepository.save(any()) } returns aNormalItem

        this.mockMvc.perform(
            MockMvcRequestBuilders.post(
    "/items/normal"
            )
                .contentType(MediaType.APPLICATION_JSON)
                .content(aNormalItemJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(aNormalItem.id))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(aNormalItem.name))
    }

    @Test
    fun createNormalItemBadRequest() {

        this.mockMvc.perform(
            MockMvcRequestBuilders.post(
    "/items/normal"
            )
                .contentType(MediaType.APPLICATION_JSON)
                .content(aBadNormalItemJson)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}
