package com.example.matule.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matule.data.remote.ShoesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoesService: ShoesService
): ViewModel() {
    var state by mutableStateOf(HomeState())

    fun load() {
        viewModelScope.launch {
            state = state.copy(shoes = shoesService.getShoesByCategories())
        }
    }
}