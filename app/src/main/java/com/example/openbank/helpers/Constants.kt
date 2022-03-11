package com.example.openbank.helpers

object Constants {
    const val PUBLIC_API_KEY = "7d37fbb18930f91a89d2c4df6bd62ac4"
    const val MARVEL_API_TS = 1
    const val PRIVATE_API_KEY = "b380886c1ed7d4ab98e17a66bb15857257b82fd1"
    const val HASH = "53779422d37994a0f22a28d2d1dec193"
    const val BASE_URL = "https://gateway.marvel.com"

    const val ID_CHARACTER = "idCharacter"

    enum class ApiError(var error: String) {
        API_ERROR("Error al obtener los productos")
    }
}