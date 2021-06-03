package com.d2back.external.graphql.query

import com.d2back.util.GraphQLTestUtil
import com.d2back.util.GraphQLTestUtil.Companion.graphQl
import com.fasterxml.jackson.databind.JsonNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ExampleExternalJWTSecuredQueryTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `get successful`() {
        val id = "123"
        val response: ResponseEntity<String> = restTemplate.graphQl(
            """
                query {
                    get(id: "$id") {
                        id
                        operationNumber
                    }
                }
                """
        )

        assertThat(response.statusCode.is2xxSuccessful).isTrue
        val responseBody: JsonNode = GraphQLTestUtil.parse(response.body!!)
        assertThat(responseBody["errors"]).isNull()
        assertThat(responseBody["data"]).isNotNull
    }
}
