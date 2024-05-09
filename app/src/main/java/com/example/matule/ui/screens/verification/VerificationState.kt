package com.example.matule.ui.screens.verification

enum class CreatingPasswordState {
    Phrase, Password
}
data class VerificationState(
    val values: List<String> = listOf("", "", "", "", "", ""),
    val time: Int = 60,
    val email: String = "",
    val checkResult: Boolean = false,
    val phrase: String = "",
    val password: String = "",
    val creatingPasswordState: CreatingPasswordState = CreatingPasswordState.Phrase
)
