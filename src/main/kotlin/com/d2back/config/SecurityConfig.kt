package com.d2back.config

import com.d2back.config.filters.APILoginFilter
import com.d2back.config.filters.CorrelationIdFilter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain
import kotlin.properties.Delegates

@Configuration
@ConfigurationProperties(prefix = "config-map.security")
@EnableWebFluxSecurity
class SecurityConfig(
    private val apiLoginFilter: APILoginFilter,
    private val correlationIdFilter: CorrelationIdFilter
) {

    var apiUsers by Delegates.notNull<Map<String, String>>()

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

        http.cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .httpBasic()

        http.authorizeExchange()
            .pathMatchers(HttpMethod.GET, "/metrics/**").permitAll()
            .pathMatchers(HttpMethod.GET, "/playground").permitAll()
            .pathMatchers("/**").authenticated()
            .and()
            .addFilterBefore(correlationIdFilter, SecurityWebFiltersOrder.AUTHORIZATION)
            .addFilterAfter(apiLoginFilter, SecurityWebFiltersOrder.AUTHORIZATION)

        return http.build()
    }

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val users = apiUsers.map { entry ->
            User.builder()
                .username(entry.key)
                .password("{noop}${entry.value}")
                .roles("API_USER")
                .build()
        }
        return MapReactiveUserDetailsService(users)
    }
}
