package com.example.matule.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.matule.data.remote.AuthService
import com.example.matule.ui.navigation.NavDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    val client: SupabaseClient
) : ViewModel() {
    var state by mutableStateOf(LoginState())

    fun login(navController: NavController) {
        viewModelScope.launch {
            if (authService.login(state.email, state.password)) {
                navController.navigate(NavDestinations.Home)
            }
        }
    }
}