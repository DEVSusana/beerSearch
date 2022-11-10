package com.proof.beersearch.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.proof.beersearch.domain.repository.Repository
import com.proof.beersearch.presentation.viewModel.ViewModel
import com.proof.beersearch.presentation.viewModel.ViewModelFactory
import com.proof.beersearch.view.compose.NavigationComponent
import com.proof.beersearch.view.ui.theme.BeerSearchTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    lateinit var viewModel: ViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var repository: Repository

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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BeerSearchTheme {
        Greeting("Android")
    }
}