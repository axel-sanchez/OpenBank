package com.example.openbank.data.model

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<CharacterDTO>,
    val total: Int
)