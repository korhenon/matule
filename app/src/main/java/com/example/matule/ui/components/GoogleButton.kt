package com.example.matule.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.matule.ui.navigation.NavDestinations
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth

@Composable
fun GoogleButton(navController: NavController, composeAuth: ComposeAuth) {
    val authState = composeAuth.rememberSignInWithGoogle(
        onResult = {
            if (it == NativeSignInResult.Success) {
                navController.navigate(NavDestinations.Home)
            }
        }
    )

    Button(onClick = {
        authState.startFlow()
    }) {
        Text(text = "Войти С Google")
    }
}