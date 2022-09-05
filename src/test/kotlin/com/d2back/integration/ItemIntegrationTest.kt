package com.d2back.integration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
class ItemIntegrationTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Test
    fun shouldReturnDefaultMessage() {
        mockMvc?.perform(
            MockMvcRequestBuilders.get(
"   /items/normal/1"
            )
        )?.andExpect(MockMvcResultMatchers.status().isOk)
    }

}
