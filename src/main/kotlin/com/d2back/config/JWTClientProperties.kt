package com.d2back.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import kotlin.properties.Delegates

@Component
@Configuration
@ConfigurationProperties(prefix = "config-map.jwt")
class JWTClientProperties {
    var enabled by Delegates.notNull<Boolean>()
    lateinit var provider: String
    lateinit var clientId: String
    lateinit var clientSecret: String
    lateinit var grantType: String
    lateinit var scope: String
}
