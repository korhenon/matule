package com.example.matule.ui.screens.listing

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
class ListingViewModel @Inject constructor(
    private val repository: ShoesRepository
): ViewModel() {
    fun toggleFavorite(shoe: Shoe) {
        viewModelScope.launch {
            repository.toggleFavorite(shoe)
            val newMap = state.shoes.toMutableMap()
            val index = newMap[shoe.category]!!.indexOf(shoe)
            val newList = newMap[shoe.category]!!.toMutableList()
            newList[index] = newList[index].copy(isFavorite = !newList[index].isFavorite)
            newMap[shoe.category] = newList
            state = state.copy(shoes=newMap)
        }
    }

    fun addToCart(shoe: Shoe) {
        viewModelScope.launch {
            repository.service.addToCart(shoe.id)
        }
    }

    var state by mutableStateOf(ListingState())
    val allShoes: List<Shoe> get() {
        val shoes = mutableListOf<Shoe>()
        for (list in state.shoes.values) {
            for (shoe in list) {
                shoes.add(shoe)
            }
        }
        return shoes
    }
}