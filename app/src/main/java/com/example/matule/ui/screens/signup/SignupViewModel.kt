package com.example.matule.ui.screens.signup

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
class SignupViewModel @Inject constructor(
    val client: SupabaseClient,
    private val authService: AuthService
): ViewModel() {
    var state by mutableStateOf(SignupState())

    fun signUp(navController: NavController) {
        viewModelScope.launch {
            if (state.isPrivacyAccepted) {
                if (authService.signUp(state.name, state.email, state.password)) {
                    navController.navigate(NavDestinations.Login)
                }
            }
        }
    }
}