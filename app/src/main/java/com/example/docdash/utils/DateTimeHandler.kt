package com.example.docdash.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateTimeHandler {
    companion object {
        fun epochSecondsToDateTime(epochSeconds: Double): String {
            val instant = Instant.ofEpochSecond(epochSeconds.toLong())
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

            // Format the LocalDateTime using a DateTimeFormatter
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return dateTime.format(formatter)
        }
    }

}