package com.martin.domain.base


import com.slack.eithernet.ApiResult

fun <T : Any, S : Any, E : Any> ApiResult<S, ErrorResultResponse>.asResult(
    onFailure: ((ApiResult.Failure<*>) -> BaseResult<T, E>)? = null,
    onHttpFailure: ((ApiResult.Failure.HttpFailure<ErrorResultResponse>) -> BaseResult<T, E>)? = null,
    onSuccess: S.() -> T
): BaseResult<T, E> {
    return when (this) {
        is ApiResult.Failure.HttpFailure -> {
            onHttpFailure?.invoke(this) ?: BaseResult.Failure(this)
        }

        is ApiResult.Failure -> {
            onFailure?.invoke(this) ?: BaseResult.Failure(this)
        }

        is ApiResult.Success -> {
            val data = onSuccess.invoke(value)
            BaseResult.Success(data)
        }
    }
}

fun <T : Any, S : Any, E : Any> ApiResult<S, ErrorResultResponse>.asResultCatching(
    onFailure: ((ApiResult.Failure<*>) -> BaseResult<T, E>)? = null,
    onHttpFailure: ((ApiResult.Failure.HttpFailure<ErrorResultResponse>) -> BaseResult<T, E>)? = null,
    onSuccess: S.() -> T
): BaseResult<T, E> {
    return when (this) {
        is ApiResult.Failure.HttpFailure -> {
            onHttpFailure?.invoke(this) ?: BaseResult.Failure(this)
        }

        is ApiResult.Failure -> {
            onFailure?.invoke(this) ?: BaseResult.Failure(this)
        }

        is ApiResult.Success -> {
            runCatching {
                val data = onSuccess.invoke(value)
                BaseResult.Success<T, E>(data)
            }.getOrElse { ex ->
                BaseResult.Failure(ApiResult.unknownFailure(ex))
            }
        }
    }
}

fun <T : Any, E : Any> ApiResult.Failure<ErrorResultResponse>.asFailureResult(
    onFailure: ((ApiResult.Failure<*>) -> BaseResult<T, E>)? = null,
    onHttpFailure: ((ApiResult.Failure.HttpFailure<ErrorResultResponse>) -> BaseResult<T, E>)? = null
): BaseResult<T, E> {
    return when (this) {
        is ApiResult.Failure.HttpFailure -> {
            onHttpFailure?.invoke(this) ?: BaseResult.Failure(this)
        }

        else -> {
            onFailure?.invoke(this) ?: BaseResult.Failure(this)
        }
    }
}

fun <T : Any, E : Any> BaseResult.Failure<T, E>.generateErrorMessage(
    apiFailureMapper: (ApiResult.Failure.ApiFailure<*>) -> String = {
        (it.error as? ErrorResultResponse)?.errors?.firstOrNull()?.message.orEmpty()
    },
    httpFailureMapper: (ApiResult.Failure.HttpFailure<*>) -> String = {
        (it.error as? ErrorResultResponse)?.errors?.firstOrNull()?.message.orEmpty()
    }
): String {
    return when (this.error) {
        is ApiResult.Failure.HttpFailure -> httpFailureMapper.invoke(this.error)
        is ApiResult.Failure.NetworkFailure -> this.error.error.message.orEmpty()
        is ApiResult.Failure.UnknownFailure -> this.error.error.message.orEmpty()
        is ApiResult.Failure.ApiFailure -> apiFailureMapper.invoke(this.error)
    }
}
