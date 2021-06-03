package com.d2back.graphql.context

import com.expediagroup.graphql.spring.execution.GRAPHQL_CONTEXT_KEY
import reactor.util.context.Context

fun Context.bffContext() = this.get<BFFContext>(GRAPHQL_CONTEXT_KEY)
