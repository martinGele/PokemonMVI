package com.martin.data.api

import com.martin.data.base.ErrorResultResponse
import com.martin.data.response.PokemonResponseDTO
import com.slack.eithernet.ApiResult
import retrofit2.http.GET


interface PokemonApi {

    @GET("/api/v2/pokemon-species")
    suspend fun getPokemon(): ApiResult<PokemonResponseDTO, ErrorResultResponse>

}