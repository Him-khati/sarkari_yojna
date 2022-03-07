package com.himanshu.sarkariyojna.data.cache.typeConverters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeTypeConverter {

    @TypeConverter
    fun toDate(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }

    @TypeConverter
    fun toLong(value: LocalDateTime): String {
        return value.toString()
    }
}