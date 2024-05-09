package com.example.matule.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ShoeDto (
    val id: Int = 0,
    val name: String,
    val description: String,
    val image: String,
    val price: Int,
    val category: String
)