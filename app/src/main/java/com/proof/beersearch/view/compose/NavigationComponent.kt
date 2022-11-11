package com.proof.beersearch.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.presentation.viewModel.ViewModel

@ExperimentalCoilApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    viewModel: ViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            val textState = remember { mutableStateOf(TextFieldValue("")) }
            if(textState.value.text.isNotEmpty()){
                viewModel.name.value = textState.value.text
                val resultList = viewModel.resultSearchBeer
                viewModel.resultItems = resultList.collectAsLazyPagingItems()

            }
            Column {
                SearchView(textState)
                DisplayList(navController = navController, viewModel, state = textState)
            }
        }
        composable(
            "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let { DetailView(viewModel, it) }
        }
    }
}