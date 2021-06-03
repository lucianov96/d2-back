package com.d2back.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@ConfigurationProperties(prefix = "config-map")
class ConfigMap {

    var environment: String = defaultEnvironment

    companion object {
        const val defaultEnvironment = "development"
    }

    fun isSafeToShowSensitiveInfo(): Boolean {
        return environment != "production"
    }
}
