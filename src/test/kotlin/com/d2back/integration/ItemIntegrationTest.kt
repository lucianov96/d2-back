package com.d2back.integration

import com.d2back.ID_ITEM
import com.d2back.aNormalItem
import com.d2back.controller.ItemController
import com.d2back.repository.NormalItemRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.Optional

@SpringBootTest
@AutoConfigureMockMvc
class ItemIntegrationTest {

    @Autowired
    lateinit var itemController: ItemController

    @MockkBean
    lateinit var normalItemRepository: NormalItemRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    val aNormalItem = aNormalItem(
        id = ID_ITEM,
        name = "Ring",
    )

    @Test
    fun shouldReturnDefaultMessage() {

        every { normalItemRepository.findById(ID_ITEM) } returns Optional.of(aNormalItem)

        this.mockMvc.perform(
            MockMvcRequestBuilders.get(
    "/items/normal/$ID_ITEM"
            )
        ).andExpect(MockMvcResultMatchers.status().isOk)
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(aNormalItem.id))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(aNormalItem.name))
    }

}
