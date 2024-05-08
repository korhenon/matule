package com.example.matule.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matule.R
import com.example.matule.ui.components.GoogleButton
import com.example.matule.ui.navigation.NavDestinations
import io.github.jan.supabase.compose.auth.composeAuth

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Привет!")
            Text(text = "Заполните Свои данные или продолжите через социальные медиа")
            Text(text = "Email")
            TextField(
                value = viewModel.state.email,
                onValueChange = { viewModel.state = viewModel.state.copy(email = it) }
            )
            Text(text = "Пароль")
            TextField(
                value = viewModel.state.password,
                onValueChange = { viewModel.state = viewModel.state.copy(password = it) },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.eye),
                        contentDescription = "",
                        Modifier.clickable {
                            viewModel.state =
                                viewModel.state.copy(isPasswordVisible = !viewModel.state.isPasswordVisible)
                        }
                    )
                },
                visualTransformation = if (viewModel.state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
            Text(
                text = "Восстановить",
                Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(NavDestinations.ForgotPassword) },
                textAlign = TextAlign.End
            )
            Button(onClick = { viewModel.login(navController) }) {
                Text(text = "Войти")
            }
            GoogleButton(navController, viewModel.client.composeAuth)
        }
        Text(
            text = "Вы впервые? Создать пользователя",
            Modifier.clickable {
                navController.navigate(NavDestinations.Signup) {
                    popUpTo(NavDestinations.Login) {
                        inclusive = true
                    }
                }
            }
        )
    }
}