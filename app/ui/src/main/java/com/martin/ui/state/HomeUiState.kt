package com.martin.ui.state

import androidx.compose.runtime.Stable

@Stable
data class HomeUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val result: List<Result>? = null,
    val next: String? = "",
    val previous: String? = ""
)

data class Result(
    val name: String,
    val url: String
)