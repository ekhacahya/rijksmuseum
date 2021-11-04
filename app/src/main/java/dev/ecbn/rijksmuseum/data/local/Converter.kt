package dev.ecbn.rijksmuseum.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import dev.ecbn.rijksmuseum.data.model.HeaderImage
import dev.ecbn.rijksmuseum.data.model.WebImage

/**
 * Created by Eka Cahya Nugraha on 03/11/21.
 * ecbn95@gmail.com
 */
class HeaderImageConverter {
    @TypeConverter
    fun storedStringToHeaderImage(json: String): HeaderImage {
        val gson = Gson()
        return gson.fromJson(json, HeaderImage::class.java)
    }

    @TypeConverter
    fun headerImageToStoredString(headerImage: HeaderImage): String {
        val gson = Gson()
        return gson.toJson(headerImage, HeaderImage::class.java)
    }
}

class WebImageConverter {
    @TypeConverter
    fun storedStringToWebImage(json: String): WebImage {
        val gson = Gson()
        return gson.fromJson(json, WebImage::class.java)
    }

    @TypeConverter
    fun webImageToStoredString(headerImage: WebImage): String {
        val gson = Gson()
        return gson.toJson(headerImage, WebImage::class.java)
    }
}