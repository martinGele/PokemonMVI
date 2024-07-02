package com.martin.domain.model

data class GetPokemon(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
) {
    data class Result(
        val name: String,
        val url: String
    )
}