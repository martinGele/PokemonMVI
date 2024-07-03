package com.martin.domain.usecase

import com.martin.data.repository.GetPokemonRepository
import com.martin.domain.base.BaseResult
import com.martin.domain.mapper.GetPokemonMapper
import com.martin.domain.model.GetPokemon
import com.martin.domain.model.GetPokemonError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val getPokemonRepository: GetPokemonRepository,
    private val getPokemonMapper: GetPokemonMapper
) {

    operator fun invoke(): Flow<BaseResult<GetPokemon, GetPokemonError>> = flow {
        emit(BaseResult.Loading())
        val pokemon = getPokemonRepository.getPokemon()
        val result = getPokemonMapper.mapAsResult(pokemon)
        emit(result)
    }
}