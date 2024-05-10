package com.example.matule.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.matule.R
import com.example.matule.data.model.Shoe
import com.example.matule.ui.navigation.NavDestinations
import com.example.matule.ui.screens.details.DetailsViewModel

@Composable
fun ShoeCard(
    shoe: Shoe,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit,
    navController: NavController,
    detailsViewModel: DetailsViewModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .background(colorScheme.surface, RoundedCornerShape(16.dp))
        .clickable {
            detailsViewModel.state =
                detailsViewModel.state.copy(shoe = shoe, isShortDescription = true)
            navController.navigate(NavDestinations.Details)
        }) {
        Icon(
            painter = painterResource(id = if (shoe.isFavorite) R.drawable.favorite_fille else R.drawable.favorite_outline),
            contentDescription = "",
            tint = if (shoe.isFavorite) Color(0xFFF87265) else Color.Gray,
            modifier = Modifier
                .padding(start = 9.dp)
                .clickable {
                    onFavoriteClick()
                }
        )
        Image(
            painter = rememberAsyncImagePainter(model = shoe.image),
            contentDescription = "",
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "Best Seller", color = colorScheme.primary,
            modifier = Modifier.padding(start = 9.dp)
        )
        Text(
            text = shoe.name,
            modifier = Modifier.padding(start = 9.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "₽${shoe.price}")
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(
                        colorScheme.primary,
                        RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .clickable {
                        onCartClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "",
                    tint = colorScheme.background
                )
            }
        }
    }
}

@Composable
fun ShoesGrid(
    shoes: List<Shoe>,
    onFavoriteClick: (Shoe) -> Unit,
    onCartClick: (Shoe) -> Unit,
    navController: NavController,
    detailsViewModel: DetailsViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(shoes) {
            ShoeCard(
                shoe = it,
                onFavoriteClick = { onFavoriteClick(it) },
                onCartClick = { onCartClick(it) },
                navController,
                detailsViewModel
            )
        }
    }
}