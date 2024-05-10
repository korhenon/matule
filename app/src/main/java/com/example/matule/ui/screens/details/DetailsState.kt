package com.example.matule.ui.screens.details

import com.example.matule.data.model.Shoe

data class DetailsState(
    val shoe: Shoe = Shoe(0, "", "", "", 100, "", false),
    val isShortDescription: Boolean = true
)
