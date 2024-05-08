package com.example.matule.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matule.ui.screens.forgotpassword.ForgotPasswordScreen
import com.example.matule.ui.screens.login.LoginScreen
import com.example.matule.ui.screens.signup.SignupScreen
import com.example.matule.ui.screens.splash.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavDestinations.Splash) {
        composable(NavDestinations.Splash) {
            SplashScreen(navController)
        }
        composable(NavDestinations.Login) {
            LoginScreen(navController)
        }
        composable(NavDestinations.Signup) {
            SignupScreen(navController)
        }
        composable(NavDestinations.ForgotPassword) {
            ForgotPasswordScreen(navController)
        }
        composable(NavDestinations.Home) {

        }
    }
}