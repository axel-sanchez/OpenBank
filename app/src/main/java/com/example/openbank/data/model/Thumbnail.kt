package com.example.openbank.data.model

data class Thumbnail(
    val extension: String,
    val path: String
){
    override fun toString(): String {
        return "${path.replace("http", "https")}.$extension"
    }
}