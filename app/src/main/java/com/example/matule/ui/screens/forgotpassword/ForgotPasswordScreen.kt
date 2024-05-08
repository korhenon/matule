package com.example.matule.ui.screens.forgotpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matule.ui.navigation.NavDestinations
import com.example.matule.ui.screens.verification.VerificationState
import com.example.matule.ui.screens.verification.VerificationViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    verificationViewModel: VerificationViewModel,
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
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(colorScheme.background, RoundedCornerShape(16.dp))
                    .clickable {
                        verificationViewModel.state = verificationViewModel.state.copy(email = viewModel.state.email)
                        navController.navigate(NavDestinations.Verification)
                    }
            ) {
                Text(text = "Проверьте Ваш Email")
                Text(text = "Мы отправили код восстановления пароля на вашу электронную почту.")
            }
        }
    }
}