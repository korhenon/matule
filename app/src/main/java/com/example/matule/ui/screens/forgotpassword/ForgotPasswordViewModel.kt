package com.example.matule.ui.screens.forgotpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matule.data.remote.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    var state by mutableStateOf(ForgotPasswordState())

    fun sendOtp() {
        viewModelScope.launch {
//            authService.sendOtp(state.email)
            state = state.copy(isModalOpen = true)
        }
    }
}