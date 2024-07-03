package com.martin.data.repository

import com.martin.data.api.PokemonApi
import com.martin.data.base.ErrorResultResponse
import com.martin.data.response.PokemonResponseDTO
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class GetPokemonRepository @Inject constructor(private val pokemonApi: PokemonApi) {

    suspend fun getPokemon(): ApiResult<PokemonResponseDTO, ErrorResultResponse> =
        pokemonApi.getPokemon()


    suspend fun getPokemonByUrl(url: String): ApiResult<PokemonResponseDTO, ErrorResultResponse> =
        pokemonApi.getPokemonByUrl(url)
}