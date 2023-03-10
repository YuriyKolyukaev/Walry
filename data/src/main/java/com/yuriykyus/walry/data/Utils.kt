package com.yuriykyus.walry.data

import android.annotation.SuppressLint
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    companion object {
        @SuppressLint("NewApi")
        fun getCity(): String {
            val zone = ZoneId.systemDefault()
            val longTimeFormatter = DateTimeFormatter.ofPattern("zzzz", Locale.getDefault())
            val timeZone = ZonedDateTime.now(zone).format(longTimeFormatter)
            val index = timeZone.indexOf(',')

            return if (index != -1) timeZone.substring(0, index)
            else timeZone
        }
    }
}