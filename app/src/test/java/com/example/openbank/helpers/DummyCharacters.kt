package com.example.openbank.helpers

import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.data.model.Thumbnail

object DummyCharacters {
    val character1 = CharacterDTO(
        1,
        "", "", "Iron Man", "",Thumbnail("", "")
    )

    val character2 = CharacterDTO(
        2,
        "", "", "America Captain", "",Thumbnail("", "")
    )

    val character3 = CharacterDTO(
        3,
        "", "", "Stranger Things", "",Thumbnail("", "")
    )

    val character4 = CharacterDTO(
        4,
        "", "", "AntMan", "",Thumbnail("", "")
    )

    fun getListCharacters(): Either<Constants.ApiError, List<CharacterDTO?>> {
        val listProducts = arrayListOf<CharacterDTO?>(character1, character2, character3, character4)
        return Either.Right(listProducts.toList())
    }
}