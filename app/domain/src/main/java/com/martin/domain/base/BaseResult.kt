package com.martin.domain.base

import com.slack.eithernet.ApiResult

sealed class BaseResult<T : Any, E : Any> {
    class Loading<T : Any, E : Any> : BaseResult<T, E>()

    data class FeatureFailure<T : Any, E : Any>(val error: E) : BaseResult<T, E>()

    data class Failure<T : Any, E : Any>(val error: ApiResult.Failure<*>) : BaseResult<T, E>()

    data class Success<T : Any, E : Any>(val data: T) : BaseResult<T, E>()
}
