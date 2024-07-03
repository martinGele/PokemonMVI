package com.martin.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponseDTO(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        val name: String,
        val url: String
    )
}