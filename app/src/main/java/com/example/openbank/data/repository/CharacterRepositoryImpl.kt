package com.example.openbank.data.repository

import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.data.source.CharacterLocalSource
import com.example.openbank.data.source.CharacterRemoteSource
import com.example.openbank.domain.repository.CharacterRepository
import com.example.openbank.helpers.Constants
import com.example.openbank.helpers.Either

/**
 * @author Axel Sanchez
 */
class CharacterRepositoryImpl(
    private val characterRemoteSource: CharacterRemoteSource,
    private val characterLocalSource: CharacterLocalSource
): CharacterRepository {
    override suspend fun getAllCharacters(): Either<Constants.ApiError, List<CharacterDTO?>> {

        val localCharacters = getLocalCharacters()
        if (localCharacters.isNotEmpty()) {
            return Either.Right(localCharacters)
        }

        val eitherRemoteCharacters = getRemoteCharacters()

        if (eitherRemoteCharacters is Either.Right) {
            addCharactersInDB(eitherRemoteCharacters.r)
            val sortedList = eitherRemoteCharacters.r.sortedBy { it?.name }
            return Either.Right(sortedList)
        }

        return eitherRemoteCharacters
    }

    override suspend fun getLocalCharacters(): List<CharacterDTO?> {
        return characterLocalSource.getAllCharacters()
    }

    override suspend fun getRemoteCharacters(): Either<Constants.ApiError, List<CharacterDTO?>> {
        return characterRemoteSource.getCharacters().value ?: Either.Left(Constants.ApiError.API_ERROR)
    }

    override suspend fun getCharacter(idCharacter: Long): CharacterDTO? {
        return characterLocalSource.getCharacter(idCharacter)
    }

    override suspend fun addCharactersInDB(result: List<CharacterDTO?>) {
        result.forEach { character ->
            character?.id = characterLocalSource.insertCharacters(character)
        }
    }
}