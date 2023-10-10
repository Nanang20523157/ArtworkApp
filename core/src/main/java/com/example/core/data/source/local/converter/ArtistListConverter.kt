package com.example.core.data.source.local.converter

import androidx.room.TypeConverter
import com.example.core.data.source.local.dataclass.ArtistList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ArtistListConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromArtistList(artistList: ArtistList?): String? {
        return gson.toJson(artistList)
    }

    @TypeConverter
    @JvmStatic
    fun toArtistList(artistListString: String?): ArtistList? {
        if (artistListString == null) {
            return null
        }
        val listType = object : TypeToken<ArtistList>() {}.type
        return gson.fromJson<ArtistList>(artistListString, listType)
    }

}