package com.example.matule.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matule.ui.components.ShoesGrid
import com.example.matule.ui.screens.details.DetailsViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.load()
    }
    Column(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
            }
            Text(text = "Избранное")
        }
        ShoesGrid(
            shoes = viewModel.state.favorites,
            onFavoriteClick = {
                viewModel.deleteFromFavorites(it)
            },
            onCartClick = {
                viewModel.addToCart(it)
            },
            navController,
            detailsViewModel
        )
    }


}