package com.d2back.graphql.context

import com.d2back.config.ConfigMap
import com.d2back.config.filters.CorrelationIdFilter.Companion.CORRELATION_ID_REACTIVE_CONTEXT_KEY
import com.expediagroup.graphql.federation.execution.FederatedGraphQLContext
import com.expediagroup.graphql.spring.execution.FederatedGraphQLContextFactory
import kotlinx.coroutines.reactor.ReactorContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.coroutines.coroutineContext

@Component
class BFFContextFactory(val configMap: ConfigMap) : FederatedGraphQLContextFactory<BFFContextFactory.FederatedBFFContext> {
    private val logger: Logger = LoggerFactory.getLogger(BFFContextFactory::class.java)

    companion object {
        const val X_APPLICATION_ID = "X-ApplicationId"
        val REQUIRED_AUTHENTICATION_HEADERS: Array<String> = arrayOf(
            X_APPLICATION_ID
        )
    }

    override suspend fun generateContext(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
    ): FederatedBFFContext {
        val correlationId = coroutineContext[ReactorContext]!!
            .context.get<String>(CORRELATION_ID_REACTIVE_CONTEXT_KEY)
        return createBFFAuthenticatedContext(request, correlationId)
    }

    private fun createBFFAuthenticatedContext(request: ServerHttpRequest, correlationId: String): FederatedBFFContext {
        validateAuthenticationHeaders(request, correlationId)

        val applicationId = request.headers.getFirst(X_APPLICATION_ID)

        return FederatedBFFContext(correlationId, AppUser(applicationId), request.headers)
    }

    private fun validateAuthenticationHeaders(request: ServerHttpRequest, correlationId: String) {
        REQUIRED_AUTHENTICATION_HEADERS.forEach { header ->
            if (request.headers.getFirst(header).isNullOrBlank()) {
                logger.debug("Unauthorized access to the requested resource. Verify all mandatory headers are present")
                throw UnauthorizedException()
            }
        }
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    class UnauthorizedException : Exception()

    class FederatedBFFContext(
        correlationId: String,
        user: AppUser,
        private val headers: HttpHeaders
    ) : BFFContext(correlationId, user),
        FederatedGraphQLContext {

        override fun getHTTPRequestHeader(caseInsensitiveHeaderName: String): String? {
            return headers.getFirst(caseInsensitiveHeaderName)
        }
    }
}
