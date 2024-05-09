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
import androidx.compose.ui.graphics.Color
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
    val modifier = if (viewModel.state.isModalOpen) Modifier
        .blur(4.dp)
        .background(Color(0x40000000))
        .clickable {
            verificationViewModel.state = verificationViewModel.state.copy(email = viewModel.state.email)
            navController.navigate(NavDestinations.Verification) } else Modifier
    Box(
        modifier.fillMaxSize()
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
    }
    if (viewModel.state.isModalOpen) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(colorScheme.background)
            ) {
                Text(text = "Проверьте Ваш Email")
                Text(text = "Мы отправили код восстановления пароля на вашу электронную почту.")
            }
        }
    }


}