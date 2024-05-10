package com.example.matule.ui.screens.listing

import com.example.matule.data.model.Shoe

data class ListingState(
    val selectedCategory: String = "Все",
    val shoes: Map<String, List<Shoe>> = mapOf()
)
