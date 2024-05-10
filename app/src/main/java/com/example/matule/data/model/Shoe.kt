package com.example.matule.data.model

data class Shoe(
    val id: Int = 0,
    val name: String,
    val description: String,
    val image: String,
    val price: Int,
    val category: String,
    val isFavorite: Boolean
)
