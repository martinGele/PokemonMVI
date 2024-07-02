package com.martin.data.repository

import com.martin.data.api.PokemonApi
import com.martin.data.response.PokemonResponseDTO
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class GetPokemonRepository @Inject constructor(private val pokemonApi: PokemonApi) {

    fun getPokemon(): ApiResult<PokemonResponseDTO, ErrorResultResponseDTO> = pokemonApi.getPokemon()
}