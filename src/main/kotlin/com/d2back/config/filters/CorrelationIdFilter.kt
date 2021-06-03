package com.d2back.config.filters

import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class CorrelationIdFilter : WebFilter {

    companion object {
        private const val CORRELATION_ID_HEADER = "X-SAN-CorrelationId"
        const val CORRELATION_ID_REACTIVE_CONTEXT_KEY = "X-SAN-CorrelationId"
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val correlationId = exchange.request.headers[CORRELATION_ID_HEADER]?.firstOrNull()
            ?: exchange.request.id

        return chain.filter(exchange).subscriberContext { context ->
            context.put(CORRELATION_ID_REACTIVE_CONTEXT_KEY, correlationId)
        }
    }
}
