package com.martin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.martin.ui.state.HomeUiAction
import com.martin.ui.state.HomeUiState
import com.martin.ui.state.HomeViewModel
import com.martin.ui.state.Result


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    HomeBody(
        uiState = state,
        onNextClicked = {
            viewModel.handleAction(HomeUiAction.NavigateToNext(state.next))
        },
        onPreviousClicked = {
            viewModel.handleAction(HomeUiAction.NavigateToNext(state.previous))

        },
        onDetailClicked = {
            viewModel.handleAction(HomeUiAction.NavigateToDetail(it))
        }
    )
}


@Composable
fun HomeBody(
    uiState: HomeUiState,
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit,
    onDetailClicked: (String) -> Unit
) {
    Column {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            uiState.result?.size?.let {
                items(uiState.result) { pokemon ->
                    PokemonItem(pokemon = pokemon, onDetailClicked = onDetailClicked)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onPreviousClicked, enabled = uiState.previous != null) {
                Text("Previous")
            }
            Button(onClick = onNextClicked, enabled = uiState.next != null) {
                Text("Next")
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Result, onDetailClicked: (String) -> Unit) {
    Text(
        text = pokemon.name, fontSize = 20.sp,
        modifier = Modifier.clickable {
            onDetailClicked(pokemon.url)
        })
}