package org.example.backend.ui.errors

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.example.backend.domain.errors.NotFoundException
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

@Component
class ErrorHandlingGraphql : DataFetcherExceptionResolverAdapter() {



    override fun resolveToSingleError(ex : Throwable, env : DataFetchingEnvironment) : GraphQLError? {

        return if (ex is NotFoundException) {
            GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(ex.message)
                .path(env.executionStepInfo.path)
                .location(env.field.sourceLocation)
                .build()
        } else {
            null
        }

    }




}
