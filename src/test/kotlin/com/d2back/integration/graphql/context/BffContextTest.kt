package com.d2back.integration.graphql.context

import com.d2back.graphql.context.BFFContext
import com.d2back.util.GraphQLTestUtil
import com.d2back.util.GraphQLTestUtil.Companion.graphQl
import com.d2back.util.SecurityTestUtil
import com.expediagroup.graphql.spring.operations.Query
import com.fasterxml.jackson.databind.JsonNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Configuration
class BffContextTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `context test query with required bff headers`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        SecurityTestUtil.addValidAuthorization(headers)
        SecurityTestUtil.addBffHeaders(headers)
        val response = restTemplate.graphQl(
            validGraphQlRequest,
            headers
        )
        val parsedResponse: JsonNode = GraphQLTestUtil.parse(response.body!!)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        // val responseContext = parsedResponse["data"]["get"]
    }

    @Test
    fun `GraphQL query without required authorization headers`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        SecurityTestUtil.addValidAuthorization(headers)
        val response = restTemplate.graphQl(
            validGraphQlRequest,
            headers
        )
        assertThat(response.statusCode.is4xxClientError).isTrue
    }

    @Test
    fun `GraphQL query with blank required authorization headers`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        SecurityTestUtil.addValidAuthorization(headers)
        val response = restTemplate.graphQl(
            validGraphQlRequest,
            headers
        )
        assertThat(response.statusCode.is4xxClientError).isTrue
    }

    val validGraphQlRequest = """
                    query {
                        get(id:"123") {
                            id,
                            operationNumber
                        }
                    }
                """
}

@Component
class ContextQueries : Query {
    fun context(context: BFFContext) = context
}
