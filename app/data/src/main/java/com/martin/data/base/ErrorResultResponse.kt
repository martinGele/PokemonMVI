package com.martin.data.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResultResponse(
    @Json(name = "Code")
    val code: String? = null,
    @Json(name = "Id")
    val id: String? = null,
    @Json(name = "Message")
    val message: String? = null,
    @Json(name = "ServiceCode")
    val serviceCode: String? = null,
    @Json(name = "Errors")
    val errors: List<ErrorResponse> = emptyList(),
    @Json(name = "AdditionalInformation")
    val additionalInformation: Map<String, String> = emptyMap(),
)


@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "ErrorId")
    val errorId: String? = null,
    @Json(name = "ErrorCode")
    val errorCode: String? = null,
    @Json(name = "Message")
    val message: String? = null
)
