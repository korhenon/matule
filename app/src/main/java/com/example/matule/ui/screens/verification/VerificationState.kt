package com.example.matule.ui.screens.verification

data class VerificationState(
    val values: List<String> = listOf("", "", "", "", "", ""),
    val time: Int = 60,
    val email: String = ""
)
