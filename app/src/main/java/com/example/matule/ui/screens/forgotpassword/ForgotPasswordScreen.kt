package com.example.matule.ui.screens.forgotpassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    Column {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
        }
        Text(text = "Забыл пароль")
        Text(text = "Введите свою учетную запись для сброса")
        TextField(
            value = viewModel.state.email,
            onValueChange = { viewModel.state = viewModel.state.copy(email = it) }

        )
        Button(onClick = {
            viewModel.sendOtp()
        }) {
            Text(text = "Отправить")
        }
    }
    if (viewModel.state.isModalOpen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(4.dp),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}