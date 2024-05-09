package com.example.matule.ui.screens.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matule.data.utils.formatTime
import kotlinx.coroutines.delay

@Composable
fun VerificationScreen(navController: NavController, viewModel: VerificationViewModel) {
    LaunchedEffect(viewModel.state.time) {
        if (viewModel.state.time in 1..60) {
            delay(1000)
            viewModel.state = viewModel.state.copy(time = viewModel.state.time - 1)
        }
    }
    val clipboardManager = LocalClipboardManager.current
    val focusRequesters = remember {
        listOf(
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester()
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(if (viewModel.state.checkResult) 4.dp else 0.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
            }
            Text(text = "OTP проверка")
            Text(text = "Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения")
            Text(text = "OTP Код")
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                for (i in 0..5) {
                    TextField(
                        value = viewModel.state.values[i],
                        onValueChange = {
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
                        },
                        modifier = (if (i == 0) Modifier else Modifier.focusRequester(
                            focusRequesters[i - 1]
                        )).weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Отправить заново", Modifier.clickable {
                    if (viewModel.state.time == 0) {
                        viewModel.resendOtp()
                    }
                })
                Text(text = viewModel.state.time.formatTime())
            }
        }
    }
    if (viewModel.state.checkResult) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(colorScheme.background)
            ) {
                if (viewModel.state.creatingPasswordState == CreatingPasswordState.Phrase) {
                    Text(text = "Введите кодовую фразу")
                    TextField(value = viewModel.state.phrase, onValueChange = {
                        viewModel.state = viewModel.state.copy(phrase = it)
                    })
                    Button(onClick = {
                        viewModel.generatePassword()
                    }) {
                        Text(text = "Сгенерировать пароль")
                    }
                } else {
                    Text(text = "Вот ваш новый пароль запомните или сохраните его где-то")
                    SelectionContainer {
                        Text(text = viewModel.state.password)
                    }
                    Button(onClick = { clipboardManager.setText(AnnotatedString(viewModel.state.password)) }) {
                        Text(text = "Копировать")
                    }
                    Button(onClick = { viewModel.changePassword(navController) }) {
                        Text(text = "Сохранить")
                    }
                }
            }
        }
    }
}

