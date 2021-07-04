package com.sd.testmo.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sd.testmo.data.entities.Owner
import java.lang.reflect.Type


open class NestedDataTypeConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<Owner?>() {}.type
    @TypeConverter
    fun stringToNestedData(json: String?): Owner {
        return gson.fromJson<Owner>(json, type)
    }

    @TypeConverter
    fun nestedDataToString(nestedData: Owner?): String {
        return gson.toJson(nestedData, type)
    }
}

