package com.example.matule.ui.screens.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matule.data.model.Shoe
import com.example.matule.domain.ShoesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: ShoesRepository
) : ViewModel() {
    fun deleteFromFavorites(shoe: Shoe) {
        viewModelScope.launch {
            repository.toggleFavorite(shoe)
            val newList = state.favorites.toMutableList()
            newList.remove(shoe)
            state = state.copy(favorites = newList)
        }
    }

    fun addToCart(shoe: Shoe) {
        viewModelScope.launch {
            repository.service.addToCart(shoe.id)
        }
    }

    var state by mutableStateOf(FavoritesState())

    fun load() {
        viewModelScope.launch {
            state = state.copy(favorites = repository.getFavorites())
        }
    }
}