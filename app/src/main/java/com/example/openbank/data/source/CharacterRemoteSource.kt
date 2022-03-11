package com.example.openbank.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.data.service.ApiServiceCharacter
import com.example.openbank.helpers.Constants.ApiError
import com.example.openbank.helpers.Constants.ApiError.*
import com.example.openbank.helpers.Either

/**
 * @author Axel Sanchez
 */
interface CharacterRemoteSource {
    suspend fun getCharacters(): MutableLiveData<Either<ApiError, List<CharacterDTO?>>>
}

class CharacterRemoteSourceImpl(private val service: ApiServiceCharacter) : CharacterRemoteSource {
    override suspend fun getCharacters(): MutableLiveData<Either<ApiError, List<CharacterDTO?>>> {
        val mutableLiveData = MutableLiveData<Either<ApiError, List<CharacterDTO?>>>()

        try {
            val response = service.getCharacters()
            Log.i("Response", response.raw().request().url().toString())
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { result ->
                    mutableLiveData.value = Either.Right(result.data.results)
                } ?: kotlin.run {
                    mutableLiveData.value = Either.Left(API_ERROR)
                }
            } else {
                Log.e("Error Response", response.errorBody().toString())
                val apiError = API_ERROR
                apiError.error = response.message()
                mutableLiveData.value = Either.Left(apiError)
            }
        } catch (e: Exception) {
            mutableLiveData.value = Either.Left(API_ERROR)
            Log.e(
                "CharacterRemoteSourceImpl",
                e.message?:"Error al obtener los personajes"
            )
            e.printStackTrace()
        }

        return mutableLiveData
    }
}