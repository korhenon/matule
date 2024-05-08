package com.example.matule.data.utils

fun Int.formatTime(): String {
    return if (this == 60) "01:00"
    else if (this < 10) "00:0${this}"
    else "00:${this}"
}