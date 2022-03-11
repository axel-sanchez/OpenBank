package com.example.openbank.di

import androidx.room.Room
import com.example.openbank.data.repository.CharacterRepositoryImpl
import com.example.openbank.data.room.Database
import com.example.openbank.data.service.ApiClient
import com.example.openbank.data.service.ApiServiceCharacter
import com.example.openbank.data.source.CharacterLocalSource
import com.example.openbank.data.source.CharacterLocalSourceImpl
import com.example.openbank.data.source.CharacterRemoteSource
import com.example.openbank.data.source.CharacterRemoteSourceImpl
import com.example.openbank.domain.repository.CharacterRepository
import com.example.openbank.domain.usecase.GetAllCharactersUseCase
import com.example.openbank.domain.usecase.GetAllCharactersUseCaseImpl
import com.example.openbank.domain.usecase.GetCharacterUseCase
import com.example.openbank.domain.usecase.GetCharacterUseCaseImpl
import com.example.openbank.helpers.Constants.BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


/**
 * @author Axel Sanchez
 */
val moduleApp = module {
    single { ApiClient.Builder<ApiServiceCharacter>()
        .setBaseUrl(BASE_URL)
        .setApiService(ApiServiceCharacter::class.java)
        .build()}
    single<CharacterRepository> { CharacterRepositoryImpl(get() as CharacterRemoteSource, get() as CharacterLocalSource) }
    single<CharacterRemoteSource> { CharacterRemoteSourceImpl(get() as ApiServiceCharacter) }
    single<CharacterLocalSource> { CharacterLocalSourceImpl((get() as Database).characterDao()) }
    single { Room
        .databaseBuilder(androidContext(), Database::class.java, "OpenBankDB.db3")
        .build() }
    single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get() as CharacterRepository) }
    single<GetCharacterUseCase> { GetCharacterUseCaseImpl(get() as CharacterRepository) }
}