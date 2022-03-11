package com.example.openbank.domain.usecase

import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.domain.repository.CharacterRepository


/**
 * @author Axel Sanchez
 */
interface GetCharacterUseCase{
    suspend fun call(idCharacter: Long): CharacterDTO?
}

class GetCharacterUseCaseImpl(private val repository: CharacterRepository): GetCharacterUseCase {
    override suspend fun call(idCharacter: Long): CharacterDTO? {
        return repository.getCharacter(idCharacter)
    }
}