package com.example.matule.ui.screens.home

import com.example.matule.data.model.Shoe

data class HomeState(
    val shoes: Map<String, List<Shoe>> = mapOf()
)
