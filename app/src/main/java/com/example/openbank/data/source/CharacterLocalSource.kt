package com.example.openbank.data.source

import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.data.room.CharacterDao

/**
 * @author Axel Sanchez
 */
interface CharacterLocalSource {
    suspend fun getAllCharacters(): List<CharacterDTO?>
    suspend fun getCharacter(idCharacter: Long): CharacterDTO?
    suspend fun insertCharacters(character: CharacterDTO?): Long
}

class CharacterLocalSourceImpl(private val database: CharacterDao): CharacterLocalSource{
    override suspend fun getAllCharacters(): List<CharacterDTO?> {
        return database.getAllCharacters()
    }

    override suspend fun insertCharacters(character: CharacterDTO?): Long {
        return database.insertCharacter(character)
    }

    override suspend fun getCharacter(idCharacter: Long): CharacterDTO? {
        return database.getCharacter(idCharacter)
    }
}