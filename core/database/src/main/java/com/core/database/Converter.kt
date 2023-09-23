package com.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromList(value: List<Int>) = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}
