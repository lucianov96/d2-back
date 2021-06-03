package com.d2back.util

import org.springframework.http.HttpHeaders

class SecurityTestUtil {

    companion object {
        private const val AUTHORIZED_API_USER = "clienteEjemplo"
        private const val AUTHORIZED_API_PASSWORD = "1234"

        fun addBffHeaders(headers: HttpHeaders) {
            listOf("X-ApplicationId")
                .forEach { headerName -> headers[headerName] = headerName }
        }

        fun addValidAuthorization(headers: HttpHeaders) {
            headers.setBasicAuth(AUTHORIZED_API_USER, AUTHORIZED_API_PASSWORD)
        }
    }
}
