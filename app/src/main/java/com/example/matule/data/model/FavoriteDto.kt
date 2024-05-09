package com.example.matule.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteDto(
    val id: Int = 0,
    @SerialName("user_id")
    val userId: String,
    @SerialName("shoe_id")
    val shoeId: Int
)
