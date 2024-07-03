package com.martin.domain.mapper

import com.martin.domain.base.BaseResult
import com.martin.data.base.ErrorResultResponse
import com.martin.domain.base.asResult
import com.martin.data.http.HttpStatusCode
import com.martin.data.response.PokemonResponseDTO
import com.martin.domain.model.GetPokemon
import com.martin.domain.model.GetPokemonError
import com.slack.eithernet.ApiResult
import javax.inject.Inject


class GetPokemonMapper @Inject constructor() {


    fun mapAsResult(response: ApiResult<PokemonResponseDTO, ErrorResultResponse>): BaseResult<GetPokemon, GetPokemonError> {
        return response.asResult(
            onHttpFailure = { apiResult ->
                when (apiResult.code) {
                    HttpStatusCode.NotFound.code -> return@asResult BaseResult.FeatureFailure(
                        GetPokemonError.NotFound
                    )

                    HttpStatusCode.InternalServerError.code -> return@asResult BaseResult.FeatureFailure(
                        GetPokemonError.ServerError
                    )

                    else -> return@asResult BaseResult.FeatureFailure(GetPokemonError.NetworkError)
                }
            },
            onFailure = {
                return@asResult BaseResult.FeatureFailure(GetPokemonError.Unknown)

            },
            onSuccess = {
                GetPokemon(
                    count = this.count,
                    next = this.next,
                    previous = this.previous,
                    results = this.results.map {
                        GetPokemon.Result(
                            name = it.name,
                            url = it.url
                        )
                    }
                )
            }
        )
    }
}