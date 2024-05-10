package com.example.matule.domain

import com.example.matule.data.model.Shoe
import com.example.matule.data.remote.ShoesService
import com.example.matule.domain.converters.toUiModel
import javax.inject.Inject

class ShoesRepository @Inject constructor(
    val service: ShoesService
) {
    suspend fun getShoesByCategories(): Map<String, List<Shoe>> {
        val shoes = service.getShoes()
        val favorites = service.getFavorites()
        val map = mutableMapOf<String, MutableList<Shoe>>()

        for (shoe in shoes) {
            val model = shoe.toUiModel(favorites.filter { it.shoeId == shoe.id }.size == 1)
            if (shoe.category in map.keys) {
                map[shoe.category]!!.add(model)
            }
            else {
                map[shoe.category] = mutableListOf(model)
            }
        }
        return map
    }

    suspend fun toggleFavorite(shoe: Shoe) {
        if (shoe.isFavorite) {
           service.deleteFavorite(shoe.id)
        } else {
            service.addFavorite(shoe.id)
        }
    }

    suspend fun getFavorites(): List<Shoe> {
        val shoes = mutableListOf<Shoe>()
        val favorites = service.getFavorites()

        for (favorite in favorites) {
            shoes.add(service.getShoe(favorite.shoeId).toUiModel(true))
        }
        return shoes
    }
}