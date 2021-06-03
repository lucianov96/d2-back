package com.d2back.util

import com.d2back.util.SecurityTestUtil.Companion.addBffHeaders
import com.d2back.util.SecurityTestUtil.Companion.addValidAuthorization
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class GraphQLTestUtil {

    companion object {

        const val ENDPOINT_GRAPHQL: String = "/api/graphql"

        fun TestRestTemplate.graphQl(graphQlRequest: String): ResponseEntity<String> {
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON
            addValidAuthorization(headers)
            addBffHeaders(headers)

            return this.graphQl(graphQlRequest, headers)
        }

        fun TestRestTemplate.graphQl(graphQlRequest: String, headers: HttpHeaders): ResponseEntity<String> {
            val payload: String = createJsonQuery(graphQlRequest)

            return this.exchange(
                ENDPOINT_GRAPHQL,
                HttpMethod.POST,
                HttpEntity(payload, headers),
                String::class.java
            )
        }

        private fun createJsonQuery(graphQL: String): String {
            return ObjectMapper().writeValueAsString(
                mapOf("query" to graphQL)
            )
        }

        fun parse(payload: String): JsonNode {
            return ObjectMapper().readTree(payload)
        }

        fun assertGraphQlErrorFields(error: JsonNode, message: String, type: String, code: String, entity: String?) {
            assertThat(error["message"].asText()).isEqualTo(message)
            assertThat(error["extensions"]["error"]["code"].asText()).isEqualTo(code)
            assertThat(error["extensions"]["error"]["type"].asText()).isEqualTo(type)
            assertThat(error["extensions"]["entity"]?.asText()).isEqualTo(entity)
        }
    }
}
