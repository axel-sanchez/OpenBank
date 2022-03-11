package com.example.openbank.domain.usecase

import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.domain.repository.CharacterRepository
import com.example.openbank.helpers.Constants
import com.example.openbank.helpers.Either

/**
 * @author Axel Sanchez
 */
interface GetAllCharactersUseCase{
    suspend fun call(): Either<Constants.ApiError, List<CharacterDTO?>>
}

class GetAllCharactersUseCaseImpl(private val repository: CharacterRepository): GetAllCharactersUseCase {
    override suspend fun call(): Either<Constants.ApiError, List<CharacterDTO?>> {
        return repository.getAllCharacters()
    }
}