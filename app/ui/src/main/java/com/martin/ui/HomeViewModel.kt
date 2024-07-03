package com.martin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.domain.base.BaseResult
import com.martin.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPokemon: GetPokemonUseCase) : ViewModel() {

    init {
        viewModelScope.launch {
            getPokemon().collectLatest {it->

               when(it){
                   is BaseResult.Loading -> {
                       // Handle loading state
                   }
                   is BaseResult.Success -> {
                       println("Martin data : ${it.data}")
                       // Handle success state
                   }
                   is BaseResult.Failure -> {
                       // Handle failure state

                   }

                   is BaseResult.FeatureFailure -> {

                   }
               }


            }
        }
    }
}