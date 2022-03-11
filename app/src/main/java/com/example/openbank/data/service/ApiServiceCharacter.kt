package com.example.openbank.data.service

import com.example.openbank.data.model.Result
import com.example.openbank.helpers.Constants
import com.example.openbank.helpers.Constants.HASH
import com.example.openbank.helpers.Constants.MARVEL_API_TS
import retrofit2.Response
import retrofit2.http.GET


/**
 * @author Axel Sanchez
 */
interface ApiServiceCharacter {
    @GET("/v1/public/characters?apikey=${Constants.PUBLIC_API_KEY}&ts=$MARVEL_API_TS&hash=$HASH")
    suspend fun getCharacters(): Response<Result?>
}