package com.d2back.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.util.UriComponentsBuilder

@Configuration
@ConfigurationProperties(prefix = "example-rest-api")
class ExampleRestAPIConfig {
    lateinit var baseURL: String

    fun exampleEntityURLGet(exampleEntityId: String): String {
        val uriComponents = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(baseURL)
            .path("/{exampleEntityId}")
            .buildAndExpand(exampleEntityId)
        return uriComponents.toUriString()
    }
}
