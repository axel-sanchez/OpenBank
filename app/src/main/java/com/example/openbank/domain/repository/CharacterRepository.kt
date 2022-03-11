package com.example.openbank.domain.repository

import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.helpers.Constants
import com.example.openbank.helpers.Either

/**
 * @author Axel Sanchez
 */
interface CharacterRepository {
    suspend fun getAllCharacters(): Either<Constants.ApiError, List<CharacterDTO?>>
    suspend fun getLocalCharacters(): List<CharacterDTO?>
    suspend fun getRemoteCharacters(): Either<Constants.ApiError, List<CharacterDTO?>>
    suspend fun getCharacter(idCharacter: Long): CharacterDTO?
    suspend fun addCharactersInDB(result: List<CharacterDTO?>)
}