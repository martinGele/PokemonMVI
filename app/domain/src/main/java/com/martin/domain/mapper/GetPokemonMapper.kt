package com.martin.domain.mapper

import com.martin.data.response.PokemonResponseDTO
import com.martin.domain.base.BaseResult
import com.martin.domain.base.ErrorResultResponse
import com.martin.domain.base.asResult
import com.martin.domain.model.GetPokemon
import com.martin.domain.model.GetPokemonError
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class GetPokemonMapper @Inject constructor() {


    fun mapAsResult(response: ApiResult<PokemonResponseDTO, ErrorResultResponse>): BaseResult<GetPokemon, GetPokemonError> {

        return response.asResult(
            onHttpFailure = { apiResult ->
                apiResult.error?.let { _ ->
                    return@asResult BaseResult.FeatureFailure(GetPokemonError.NetworkError)
                }
                return@asResult BaseResult.Failure(apiResult)
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