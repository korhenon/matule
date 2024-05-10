package com.example.matule.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matule.R
import com.example.matule.ui.components.ShoeCard
import com.example.matule.ui.navigation.NavDestinations
import com.example.matule.ui.screens.details.DetailsViewModel
import com.example.matule.ui.screens.listing.ListingViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel,
    listingViewModel: ListingViewModel,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.load()
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                }
                IconButton(onClick = { navController.navigate(NavDestinations.Favorites) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite_outline),
                        contentDescription = ""
                    )
                }
            }
            Text(text = "Главная")
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Row(
                Modifier
                    .weight(1f)
                    .background(colorScheme.surface)
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
                Text(text = "Поиск")
            }
            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorScheme.primary,
                    contentColor = colorScheme.background
                )
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "")
            }
        }
        Text(text = "Категории")
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CategoryBox(category = "Все", onClick = {
                listingViewModel.state = listingViewModel.state.copy(
                    shoes = viewModel.state.shoes,
                    selectedCategory = "Все"
                )
                navController.navigate(NavDestinations.Listing)
            })
            for (category in viewModel.state.shoes.keys) {
                CategoryBox(category = category, onClick = {
                    listingViewModel.state = listingViewModel.state.copy(
                        shoes = viewModel.state.shoes,
                        selectedCategory = category
                    )
                    navController.navigate(NavDestinations.Listing)
                })
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Популярное")
            Text(text = "Все")
        }
        if (viewModel.allShoes.size >= 2) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                ShoeCard(shoe = viewModel.allShoes[0], {
                    viewModel.toggleFavorite(viewModel.allShoes[0])
                }, {
                    viewModel.addToCart(viewModel.allShoes[0])
                }, navController, detailsViewModel, Modifier.weight(1f))
                ShoeCard(shoe = viewModel.allShoes[1], {
                    viewModel.toggleFavorite(viewModel.allShoes[1])
                }, {
                    viewModel.addToCart(viewModel.allShoes[1])
                }, navController, detailsViewModel, Modifier.weight(1f))
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Акции")
            Text(text = "Все")
        }
    }
}


@Composable
fun CategoryBox(category: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Box(
        modifier = Modifier
            .width(108.dp)
            .height(40.dp)
            .background(colorScheme.surface)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = category)
    }
}

