package com.proof.beersearch.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.proof.beersearch.domain.repository.Repository
import com.proof.beersearch.presentation.viewModel.ViewModel
import com.proof.beersearch.presentation.viewModel.ViewModelFactory
import com.proof.beersearch.view.compose.NavigationComponent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var viewModel: ViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var repository: Repository

    @SuppressLint("CheckResult")
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NavigationComponent(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
