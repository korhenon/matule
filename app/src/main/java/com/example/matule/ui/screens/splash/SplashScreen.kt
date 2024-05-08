package com.example.matule.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matule.ui.navigation.NavDestinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    LaunchedEffect(true) {
        delay(300)
        if (viewModel.isAuthenticated != null) {
            navController.navigate(NavDestinations.Home)
        } else {
            navController.navigate(NavDestinations.Login)

        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(colorScheme.primary, colorScheme.secondary))),
        contentAlignment = Alignment.Center
    ) {
        Text(text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append("MATULE")
            }
            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.Light,
                    baselineShift = BaselineShift.Superscript
                )
            ) {
                append("ME")
            }
        }, fontSize = 65.sp, color = colorScheme.onPrimary)
    }
}