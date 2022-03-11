package com.example.openbank.presentation.viewmodels

import androidx.lifecycle.*
import com.example.openbank.data.model.CharacterDTO
import com.example.openbank.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.launch


/**
 * @author Axel Sanchez
 */
class DetailsViewModel(private val getCharacterUseCase: GetCharacterUseCase) : ViewModel() {

    private val listData: MutableLiveData<CharacterDTO?> = MutableLiveData<CharacterDTO?>()

    private fun setListData(result: CharacterDTO?) {
        listData.postValue(result)
    }

    fun getCharacter(idCharacter: Long) {
        viewModelScope.launch {
            setListData(getCharacterUseCase.call(idCharacter))
        }
    }

    fun getCharacterLiveData(): LiveData<CharacterDTO?> {
        return listData
    }

    class DetailsViewModelFactory(private val getCharacterUseCase: GetCharacterUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetCharacterUseCase::class.java)
                .newInstance(getCharacterUseCase)
        }
    }
}