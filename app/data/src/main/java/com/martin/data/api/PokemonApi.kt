package com.martin.data.api

import com.martin.data.response.PokemonResponseDTO
import com.slack.eithernet.ApiResult
import com.slack.eithernet.DecodeErrorBody
import retrofit2.http.POST


interface PokemonApi {

    @DecodeErrorBody
    @POST("v2.0/users/refresh")
    fun getPokemon(): ApiResult<PokemonResponseDTO, ErrorResultResponseDTO>

}