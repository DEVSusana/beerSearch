package com.proof.beersearch.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.presentation.viewModel.ViewModel

@ExperimentalCoilApi
@Composable
fun DisplayList(navController: NavController, viewModel: ViewModel) {
    val selectedIndex by remember { mutableStateOf(-1) }
    val resultList = viewModel.resultListBeer
    val resultItems: LazyPagingItems<ApiResponse> = resultList.collectAsLazyPagingItems()
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            itemsIndexed(
                resultItems
            ) { index, item ->
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