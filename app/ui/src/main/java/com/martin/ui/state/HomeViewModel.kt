package com.martin.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.domain.base.BaseResult
import com.martin.domain.usecase.GetNextPreviousPokemonUseCase
import com.martin.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemon: GetPokemonUseCase,
    private val getPokemonByUrl: GetNextPreviousPokemonUseCase
) : ViewModel() {


    private val setUiState = MutableStateFlow(HomeUiState())
    val uiState = setUiState.asStateFlow()

    init {
        getPokemon()
            .flowOn(Dispatchers.IO)
            .onEach { data ->
                when (data) {
                    is BaseResult.Success -> {
                        setUiState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                result = data.data.results.map { result ->
                                    Result(
                                        name = result.name,
                                        url = result.url
                                    )
                                },
                                next = data.data.next,
                                previous = data.data.previous
                            )
                        }
                    }
                    is BaseResult.FeatureFailure -> {
                        setUiState.update {
                            it.copy(
                                isLoading = false,
                                isError = true
                            )
                        }
                    }

                    else -> {}
                }

            }.launchIn(viewModelScope)
    }

    fun handleAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.NavigateToDetail -> {
                // navigate to detail screen
            }

            is HomeUiAction.NavigateToNext -> {
                action.next?.let { getNextPreviousPokemon(it) }
            }

            is HomeUiAction.NavigateToPrevious -> {
                action.previous?.let { getNextPreviousPokemon(it) }
            }
        }
    }

    private fun getNextPreviousPokemon(url: String) {
        getPokemonByUrl(url).flowOn(Dispatchers.IO)
            .onEach { data ->
                when (data) {
                    is BaseResult.Success -> {
                        setUiState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                result = data.data.results.map { result ->
                                    Result(
                                        name = result.name,
                                        url = result.url
                                    )
                                },
                                next = data.data.next,
                                previous = data.data.previous
                            )
                        }
                    }

                    is BaseResult.FeatureFailure -> {
                        setUiState.update {
                            it.copy(
                                isLoading = false,
                                isError = true
                            )
                        }
                    }
                    else -> {}
                }

            }.launchIn(viewModelScope)
    }
}
