package com.example.matule.data.remote

import androidx.lifecycle.ViewModel
import com.example.matule.data.model.CartDto
import com.example.matule.data.model.FavoriteDto
import com.example.matule.data.model.ShoeDto
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Count
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoesService @Inject constructor(
    private val postgrest: Postgrest,
    private val authService: AuthService
) : ViewModel() {
    suspend fun getShoes(): List<ShoeDto> {
        return withContext(Dispatchers.IO) {
            postgrest.from("shoes").select().decodeList()
        }
    }

    suspend fun getShoe(shoeId: Int): ShoeDto {
        return withContext(Dispatchers.IO) {
            postgrest.from("shoes").select { filter { eq("id", shoeId) } }.decodeList<ShoeDto>()[0]
        }
    }

    suspend fun getFavorites(): List<FavoriteDto> {
        val user = authService.getUserInfo()
        return withContext(Dispatchers.IO) {
            postgrest.from("favorites").select { filter { eq("user_id", user!!.id) } }.decodeList()
        }
    }

    suspend fun addFavorite(shoeId: Int) {
        val user = authService.getUserInfo()
        withContext(Dispatchers.IO) {
            postgrest.from("favorites").insert(FavoriteDto(userId = user!!.id, shoeId = shoeId))
        }
    }

    suspend fun deleteFavorite(shoeId: Int) {
        val user = authService.getUserInfo()
        withContext(Dispatchers.IO) {
            postgrest.from("favorites").delete {
                filter {
                    eq("user_id", user!!.id)
                    eq("shoe_id", shoeId)
                }
            }
        }
    }

    suspend fun getCart(): List<CartDto> {
        val user = authService.getUserInfo()
        return withContext(Dispatchers.IO) {
            postgrest.from("cart").select { filter { eq("user_id", user!!.id)} }.decodeList()
        }
    }

    suspend fun addToCart(shoeId: Int) {
        val user = authService.getUserInfo()
        withContext(Dispatchers.IO) {
            postgrest.from("cart").insert(CartDto(userId = user!!.id, shoeId = shoeId, count = 1))
        }
    }

    suspend fun changeCount(shoeId: Int, newCount: Int) {
        val user = authService.getUserInfo()
        withContext(Dispatchers.IO) {
            postgrest.from("cart").update({
                set("count", newCount)
            }) {
                filter {
                    eq("user_id", user!!.id)
                    eq("shoe_id", shoeId)
                }
            }
        }
    }

    suspend fun deleteFromCart(shoeId: Int) {
        val user = authService.getUserInfo()
        withContext(Dispatchers.IO) {
            postgrest.from("cart").delete {
                filter {
                    eq("user_id", user!!.id)
                    eq("shoe_id", shoeId)
                }
            }
        }
    }
}