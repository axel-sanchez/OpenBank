package com.example.openbank.data.room

import androidx.room.TypeConverter
import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.data.model.Thumbnail
import com.google.gson.Gson

/**
 * @author Axel Sanchez
 */
class Converters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromCharacter(character: CharacterDTO?): String? {
        character?.let {
            return gson.toJson(it)
        } ?: return null
    }

    @TypeConverter
    fun toCharacter(resultItemString: String?): CharacterDTO? {
        resultItemString?.let {
            return gson.fromJson(it, CharacterDTO::class.java)
        } ?: return null
    }

    @TypeConverter
    fun fromListCharacters(list: List<CharacterDTO?>?): String? {
        var response = ""
        list?.let {
            for (i in list.indices) {
                response += if (i == 0) fromCharacter(it[i])
                else ";${fromCharacter(it[i])}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toListCharacters(concat: String?): List<CharacterDTO?>? {
        val newList = concat?.split(";")
        newList?.let {
            return it.map { str -> toCharacter(str) }
        } ?: return null
    }

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail?): String? {
        thumbnail?.let {
            return gson.toJson(it)
        } ?: return null
    }

    @TypeConverter
    fun toThumbnail(resultItemString: String?): Thumbnail? {
        resultItemString?.let {
            return gson.fromJson(it, Thumbnail::class.java)
        } ?: return null
    }
}