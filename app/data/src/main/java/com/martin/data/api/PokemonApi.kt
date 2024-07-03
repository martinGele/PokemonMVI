package com.martin.data.api

import com.martin.data.base.ErrorResultResponse
import com.martin.data.response.PokemonResponseDTO
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Url


interface PokemonApi {

    @GET("/api/v2/pokemon-species")
    suspend fun getPokemon(): ApiResult<PokemonResponseDTO, ErrorResultResponse>

    @GET
    suspend fun getPokemonByUrl(@Url url: String): ApiResult<PokemonResponseDTO, ErrorResultResponse>
}