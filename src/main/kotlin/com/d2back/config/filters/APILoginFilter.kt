package com.d2back.config.filters

import com.d2back.config.filters.CorrelationIdFilter.Companion.CORRELATION_ID_REACTIVE_CONTEXT_KEY
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.Base64

@Component
class APILoginFilter : WebFilter {

    companion object {
        const val metricsPath: String = "/metrics"
        const val basicAuthorizationValue: String = "Basic"
        const val authorizationHeader: String = "Authorization"
    }

    private val logger: Logger = LoggerFactory.getLogger(APILoginFilter::class.java)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        return Mono.deferWithContext { ctx ->
            val authorization = (
                exchange.request.headers[authorizationHeader]?.firstOrNull()
                    ?: ""
                ).replace(basicAuthorizationValue, "").trim()

            val path = exchange.request.path.pathWithinApplication().value()

            if (!path.startsWith(metricsPath) && authorization.isNotBlank()) {
                val correlationId = ctx.get<String>(CORRELATION_ID_REACTIVE_CONTEXT_KEY)

                var apiUser = "N/A"

                try {
                    val decodedAuthorizationBytes: ByteArray = Base64.getDecoder().decode(authorization)
                    val decodedAuthorization = String(decodedAuthorizationBytes)
                    val authorizationParams = decodedAuthorization.split(":")
                    apiUser = authorizationParams.first()
                } catch (exception: Exception) {
                    logger.info("API Client login: failed. User: $apiUser")
                }
            }

            return@deferWithContext chain.filter(exchange)
        }
    }
}
