package com.example.openbank.presentation.viewmodels

import androidx.lifecycle.*
import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.domain.usecase.GetAllCharactersUseCase
import com.example.openbank.helpers.Constants
import com.example.openbank.helpers.Either
import kotlinx.coroutines.launch

/**
 * @author Axel Sanchez
 */
class CharacterViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase): ViewModel() {

    private val listData: MutableLiveData<Either<Constants.ApiError, List<CharacterDTO?>>> by lazy {
        MutableLiveData<Either<Constants.ApiError, List<CharacterDTO?>>>().also {
            getCharacters()
        }
    }

    private fun setListData(result: Either<Constants.ApiError, List<CharacterDTO?>>) {
        listData.postValue(result)
    }

    private fun getCharacters() {
        viewModelScope.launch {
            setListData(getAllCharactersUseCase.call())
        }
    }

    fun getCharactersLiveData(): LiveData<Either<Constants.ApiError, List<CharacterDTO?>>> {
        return listData
    }

    class CharacterViewModelFactory(private val getAllCharactersUseCase: GetAllCharactersUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetAllCharactersUseCase::class.java).newInstance(getAllCharactersUseCase)
        }
    }
}