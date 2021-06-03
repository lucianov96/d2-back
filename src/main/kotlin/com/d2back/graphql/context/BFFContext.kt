package com.d2back.graphql.context

import com.expediagroup.graphql.execution.GraphQLContext

open class BFFContext(val correlationId: String, val user: AppUser) : GraphQLContext {
    fun authenticatedUser(): AppUser {
        return user
    }
}
