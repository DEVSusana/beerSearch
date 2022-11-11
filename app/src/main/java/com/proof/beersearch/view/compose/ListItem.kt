package com.proof.beersearch.view.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.proof.beersearch.data.model.ApiResponse

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListItem(
    navController: NavController,
    detail: ApiResponse,
    index: Int,
    selectedIndex: Int
) {
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Surface(color = backgroundColor) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate("details/${detail.id}") },
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageBeer(detail = detail)
                Column {
                    Text(text = detail.name, style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}