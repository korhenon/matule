package com.example.matule.ui.screens.signup

data class SignupState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isPrivacyAccepted: Boolean = false
)