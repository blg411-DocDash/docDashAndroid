package com.example.docdash.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateTimeHandler {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun epochSecondsToDateTime(epochSeconds: Long): String {
            val instant = Instant.ofEpochSecond(epochSeconds)
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            // Adding a leading zero to the month and day if they are single digits
            // Format the LocalDateTime using a DateTimeFormatter
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return dateTime.format(formatter)
        }
    }
}