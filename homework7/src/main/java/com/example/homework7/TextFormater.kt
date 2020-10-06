package com.example.homework7

import java.util.*

fun actionToLog(action: String): String {
    val calendar = Calendar.getInstance()
    return StringBuilder()
            .append(calendar.get(Calendar.YEAR)).append("/")
            .append(calendar.get(Calendar.MONTH) + 1).append("/")
            .append(calendar.get(Calendar.DAY_OF_MONTH)).append("_")
            .append(calendar.get(Calendar.HOUR_OF_DAY)).append(":")
            .append(calendar.get(Calendar.MINUTE)).append("-")
            .append(action)
            .toString()
}