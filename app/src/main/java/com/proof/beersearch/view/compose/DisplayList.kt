package com.proof.beersearch.view.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
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
        LazyColumn(modifier = modifier,
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