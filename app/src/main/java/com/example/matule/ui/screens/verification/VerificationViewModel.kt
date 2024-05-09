package com.example.matule.ui.screens.verification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.matule.data.remote.AuthService
import com.example.matule.data.utils.generatePassword
import com.example.matule.ui.navigation.NavDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    var state by mutableStateOf(VerificationState())

    fun resendOtp() {
        viewModelScope.launch {
            authService.sendOtp(state.email)
        }
        state = state.copy(time = 60)
    }

    fun checkOtp() {
        viewModelScope.launch {
            state = state.copy(
                checkResult = authService.checkOtp(
                    state.email,
                    state.values.joinToString("")
                )
            )
        }
    }

    fun generatePassword() {
        state = state.copy(
            password = state.phrase.generatePassword(),
            creatingPasswordState = CreatingPasswordState.Password
        )
    }

    fun changePassword(navController: NavController) {
        viewModelScope.launch {
            authService.changePassword(state.password)
            navController.navigate(NavDestinations.Home)
        }
    }
}