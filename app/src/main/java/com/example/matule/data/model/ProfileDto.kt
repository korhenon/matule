package com.example.matule.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ProfileDto(
    @SerialName("user_id")
    val id: String,
    val name: String
)
