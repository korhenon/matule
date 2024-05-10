package com.example.matule.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(): ViewModel() {
    var state by mutableStateOf(DetailsState())
    val shortDescription get() = state.shoe.description.split(" ").take(15).joinToString(" ") + "..."
}