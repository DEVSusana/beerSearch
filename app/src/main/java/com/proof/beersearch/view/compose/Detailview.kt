package com.proof.beersearch.view.compose

import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.presentation.viewModel.ViewModel


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun DetailView(viewModel: ViewModel, id: Int) {
    viewModel.getBeerDetailResponse(id)

    when (viewModel.getBeerDetail.value) {
        is Resource.Success -> {
            val detail by remember {
                mutableStateOf(viewModel.getBeerDetail.value)
            }
            viewModel.getBeerDetail.value?.data.let {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (detail != null) {
                        detail!!.data?.beerList?.get(0)
                            ?.let { it1 -> ImageBeer(detail = it1) }
                    }
                    Column {
                       detail!!.data?.beerList?.get(0)?.let {
                           Text(text = it.name)
                           Spacer(modifier = Modifier.height(4.dp))
                           Text(text = it.description)
                       }
                    }
                }
            }
        }
        is Resource.Error<*> -> {
            (viewModel.getBeerDetail.value as Resource.Error<ApiResponse>).message?.let {
                Toast.makeText(LocalContext.current, "An error occurred : $it", Toast.LENGTH_LONG)
                    .show()
                Log.i("ERROR", it)
            }
        }

        is Resource.Loading<*> -> {
            ShowProgressBar()
        }

        else -> {
            (viewModel.getBeerDetail.value as Resource.Error<ApiResponse>).message?.let {
                Toast.makeText(LocalContext.current, "An error occurred : $it", Toast.LENGTH_LONG)
                    .show()
                Log.i("ERROR", it)
            }
        }
    }

}