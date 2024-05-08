package com.example.matule.ui.screens.verification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matule.data.utils.formatTime
import kotlinx.coroutines.delay

@Composable
fun VerificationScreen(navController: NavController, viewModel: VerificationViewModel) {
    LaunchedEffect(true) {
        if (viewModel.state.time in 1..60) {
            delay(100)
            viewModel.state = viewModel.state.copy(time = viewModel.state.time - 1)
        }
    }
    val focusRequesters = remember { listOf(FocusRequester(), FocusRequester(), FocusRequester(), FocusRequester(), FocusRequester()) }
    Column(Modifier.fillMaxWidth()) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
        }
        Text(text = "OTP проверка")
        Text(text = "Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения")
        Text(text = "OTP Код")
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            for (i in 0..5) {
                TextField(value = viewModel.state.values[i], onValueChange = {
                    if (it.length <= 1) {
                        val newList = viewModel.state.values.toMutableList()
                        newList[i] = it
                        viewModel.state = viewModel.state.copy(values = newList)
                    }
                    if (it.length == 1 && i < 5) {
                        focusRequesters[i].requestFocus()
                    }
                    if (it.length == 1 && i == 5) {
                        viewModel.checkOtp()
                    }
                }, modifier = (if (i == 0) Modifier else Modifier.focusRequester(focusRequesters[i - 1])).weight(1f))
            }
        }
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Отправить заново", Modifier.clickable {
                viewModel.resendOtp()
            })
            Text(text = viewModel.state.time.formatTime())
        }
    }
}

