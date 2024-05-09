package com.example.matule.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    val id: Int = 0,
    @SerialName("user_id")
    val userId: String,
    @SerialName("shoe_id")
    val shoeId: Int,
    val count: Int
)