package com.martin.domain.model

sealed class GetPokemonError {
    data object NetworkError : GetPokemonError()
    data object Unknown : GetPokemonError()
    data object ServerError : GetPokemonError()
    data object NotFound : GetPokemonError()
}