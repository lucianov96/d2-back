package com.d2back.integration.configuration

import com.d2back.util.GraphQLTestUtil.Companion.graphQl
import com.d2back.util.SecurityTestUtil.Companion.addBffHeaders
import com.d2back.util.SecurityTestUtil.Companion.addValidAuthorization
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityConfigTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private val validGraphQlRequest = """
                    query {
                        dummy {
                            dummy
                        }
                    }
                    """

    @Test
    fun `GraphQL query without api key`() {
        val emptyHeaders = HttpHeaders()
        val response = restTemplate.graphQl(
            validGraphQlRequest,
            emptyHeaders
        )
        assertThat(response.statusCode.is4xxClientError).isTrue
    }

    @Test
    fun `GraphQL query without required authorization headers`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        addValidAuthorization(headers)
        val response = restTemplate.graphQl(
            validGraphQlRequest,
            headers
        )
        assertThat(response.statusCode.is4xxClientError).isTrue
    }

    @Test
    fun `GraphQL query with api key and with required authorization headers`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBasicAuth("clienteEjemplo", "1234")
        addBffHeaders(headers)

        val response = restTemplate.graphQl(
            validGraphQlRequest,
            headers
        )
        assertThat(response.statusCode.is2xxSuccessful).isTrue
    }

    @Test
    fun `GraphQL query with alternative api key and with required authorization headers`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBasicAuth("clienteEjemplo2", "5678")
        addBffHeaders(headers)

        val response = restTemplate.graphQl(
            validGraphQlRequest,
            headers
        )

        assertThat(response.statusCode.is2xxSuccessful).isTrue
    }

    @Test
    fun `GraphQL query with invalid api key and with required authorization headers`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBasicAuth("????", "????")
        addBffHeaders(headers)

        val response = restTemplate.graphQl(
            validGraphQlRequest,
            headers
        )

        assertThat(response.statusCode.is4xxClientError).isTrue
    }
}
