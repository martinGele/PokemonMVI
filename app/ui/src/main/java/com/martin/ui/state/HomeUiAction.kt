package com.martin.ui.state

sealed interface HomeUiAction {
    data class NavigateToDetail(val url: String) : HomeUiAction
    data class NavigateToNext(val next: String?) : HomeUiAction
    data class NavigateToPrevious(val previous: String?) : HomeUiAction
}