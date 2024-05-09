package com.example.matule.ui.screens.home

import com.example.matule.data.model.ShoeDto

data class HomeState(
    val shoes: List<ShoeDto> = listOf()
)
