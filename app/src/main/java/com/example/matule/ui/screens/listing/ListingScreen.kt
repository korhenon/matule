package com.example.matule.ui.screens.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.matule.ui.components.ShoesGrid
import com.example.matule.ui.screens.details.DetailsViewModel
import com.example.matule.ui.screens.home.CategoryBox

@Composable
fun ListingScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel,
    viewModel: ListingViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
            }
            Text(text = viewModel.state.selectedCategory)
        }
        Text(text = "Категории")
        Row {
            CategoryBox(category = "Все", onClick = {
                viewModel.state = viewModel.state.copy(selectedCategory = "Все")
            }, isSelected = viewModel.state.selectedCategory == "Все")
            for (category in viewModel.state.shoes.keys) {
                CategoryBox(category = category, onClick = {
                    viewModel.state = viewModel.state.copy(selectedCategory = category)
                }, isSelected = viewModel.state.selectedCategory == category)
            }
        }
        ShoesGrid(
            shoes = if (viewModel.state.selectedCategory == "Все") viewModel.allShoes else viewModel.state.shoes[viewModel.state.selectedCategory]!!,
            onFavoriteClick = {
                viewModel.toggleFavorite(it)
            },
            onCartClick = {
                viewModel.addToCart(it)
            },
            navController,
            detailsViewModel
        )
    }
}