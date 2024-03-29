package com.proof.beersearch.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.presentation.viewModel.ViewModel

@ExperimentalCoilApi
@Composable
fun DisplayList(
    navController: NavController, viewModel: ViewModel,
    state: MutableState<TextFieldValue>, modifier: Modifier = Modifier
) {
    val selectedIndex by remember { mutableStateOf(-1) }
    val resultItems: LazyPagingItems<ApiResponse> = if (state.value.text.isNotEmpty()) {
        viewModel.resultSearchBeer.collectAsLazyPagingItems()
    } else {
        viewModel.resultListBeer.collectAsLazyPagingItems()
    }

    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                count = resultItems.itemCount
            ) { index ->
                val item = resultItems[index]
                if (item != null) {
                    ListItem(navController = navController, detail = item, index, selectedIndex)
                }
            }
        }
    }
    resultItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                //You can add modifier to manage load state when first time response page is loading
                ShowProgressBar()
            }
            loadState.append is LoadState.Loading -> {
                //You can add modifier to manage load state when next response page is loading
                ShowProgressBar()
            }
            loadState.append is LoadState.Error -> {
                //You can use modifier to show error message
            }
        }
    }


}