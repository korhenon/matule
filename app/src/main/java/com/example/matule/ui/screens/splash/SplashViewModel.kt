package com.example.matule.ui.screens.splash

import androidx.lifecycle.ViewModel
import com.example.matule.data.remote.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    val isAuthenticated get() = authService.getUserInfo()
}