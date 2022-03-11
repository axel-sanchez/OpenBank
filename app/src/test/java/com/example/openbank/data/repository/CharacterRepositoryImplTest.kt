package com.example.openbank.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.openbank.data.source.CharacterLocalSource
import com.example.openbank.data.source.CharacterRemoteSource
import com.example.openbank.domain.repository.CharacterRepository
import com.example.openbank.helpers.DummyCharacters.character1
import com.example.openbank.helpers.DummyCharacters.character2
import com.example.openbank.helpers.DummyCharacters.character3
import com.example.openbank.helpers.DummyCharacters.character4
import com.example.openbank.helpers.DummyCharacters.getListCharacters
import com.example.openbank.helpers.Either
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class CharacterRepositoryImplTest{
    private val characterRemoteSource: CharacterRemoteSource = Mockito.mock(CharacterRemoteSource::class.java)
    private val characterLocalSource: CharacterLocalSource = Mockito.mock(CharacterLocalSource::class.java)
    private val characterRepository: CharacterRepository = CharacterRepositoryImpl(characterRemoteSource, characterLocalSource)

    @Test
    fun should_return_character_list_sorted_by_title() {
        runBlocking {
            BDDMockito.given(characterRepository.getLocalCharacters()).willReturn(listOf())

            val mutableListData = MutableLiveData(getListCharacters())
            BDDMockito.given(characterRemoteSource.getCharacters()).willReturn(mutableListData)

            BDDMockito.given(characterLocalSource.insertCharacters(character1)).willReturn(1)
            BDDMockito.given(characterLocalSource.insertCharacters(character2)).willReturn(2)
            BDDMockito.given(characterLocalSource.insertCharacters(character3)).willReturn(3)
            BDDMockito.given(characterLocalSource.insertCharacters(character4)).willReturn(4)

            val result = characterRepository.getAllCharacters()
            MatcherAssert.assertThat((result as Either.Right).r, Matchers.contains(character2, character4, character1, character3))
        }
    }

    @Test
    fun should_calls_to_getRemoteProducts_when_there_are_not_local_products(){
        runBlocking {
            val mutableListData = MutableLiveData(getListCharacters())
            BDDMockito.given(characterRemoteSource.getCharacters()).willReturn(mutableListData)

            BDDMockito.given(characterLocalSource.insertCharacters(character1)).willReturn(1)
            BDDMockito.given(characterLocalSource.insertCharacters(character2)).willReturn(2)
            BDDMockito.given(characterLocalSource.insertCharacters(character3)).willReturn(3)
            BDDMockito.given(characterLocalSource.insertCharacters(character4)).willReturn(4)

            BDDMockito.given(characterRepository.getLocalCharacters()).willReturn(listOf())

            characterRepository.getAllCharacters()
            Mockito.verify(characterRemoteSource).getCharacters()
        }
    }

    @Test
    fun should_not_calls_to_getRemoteProducts_when_there_are_local_products(){
        runBlocking {
            val mutableListData = MutableLiveData(getListCharacters())
            BDDMockito.given(characterRemoteSource.getCharacters()).willReturn(mutableListData)

            BDDMockito.given(characterLocalSource.insertCharacters(character1)).willReturn(1)
            BDDMockito.given(characterLocalSource.insertCharacters(character2)).willReturn(2)
            BDDMockito.given(characterLocalSource.insertCharacters(character3)).willReturn(3)
            BDDMockito.given(characterLocalSource.insertCharacters(character4)).willReturn(4)

            BDDMockito.given(characterRepository.getLocalCharacters()).willReturn(listOf(character1))

            characterRepository.getAllCharacters()
            Mockito.verify(characterRemoteSource, BDDMockito.never()).getCharacters()
        }
    }
}