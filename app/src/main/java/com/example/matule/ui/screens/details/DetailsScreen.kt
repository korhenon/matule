package com.example.matule.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.matule.R

@Composable
fun DetailsScreen(navController: NavHostController, viewModel: DetailsViewModel) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
        }
        Text(text = viewModel.state.shoe.name)
        Text(text = "₽${viewModel.state.shoe.price}")
        Image(painter = rememberAsyncImagePainter(model = viewModel.state.shoe.image), contentDescription = "")
        Text(text = if (viewModel.state.isShortDescription) viewModel.shortDescription else viewModel.state.shoe.description)
        if (viewModel.state.isShortDescription) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Text(text = "Подробнее", Modifier.clickable {
                    viewModel.state = viewModel.state.copy(isShortDescription = false)
                })
            }
        }
    }
}