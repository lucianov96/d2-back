package com.d2back.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
abstract class MockWebServerTest {
    protected lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun setupMockWebServer(context: ApplicationContext) {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @AfterEach
    fun teardownMockWebServer(context: ApplicationContext) {
        mockWebServer.shutdown()
    }
}

fun MockResponse.json(json: String) =
    this.setHeader("Content-Type", "application/json;charset=utf-8")
        .setBody(json)
