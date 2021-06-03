package com.d2back.config

import com.d2back.graphql.context.BFFContext
import com.expediagroup.graphql.spring.GraphQLAutoConfiguration
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLErrorConfig(val configMap: ConfigMap) : GraphQLAutoConfiguration() {

    override fun exceptionHandler(): DataFetcherExceptionHandler {
        return ExceptionHandler(configMap)
    }

    class ExceptionHandler(private val configMap: ConfigMap) : DataFetcherExceptionHandler {

        override fun onException(params: DataFetcherExceptionHandlerParameters): DataFetcherExceptionHandlerResult {
            val bffContext = params.dataFetchingEnvironment.getContext() as BFFContext

            return DataFetcherExceptionHandlerResult.Builder().build()
        }
    }
}
