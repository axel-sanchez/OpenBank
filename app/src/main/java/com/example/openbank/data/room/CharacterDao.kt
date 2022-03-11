package com.example.openbank.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openbank.data.model.CharacterDTO

/**
 * @author Axel Sanchez
 */
@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterDTO ORDER BY name")
    suspend fun getAllCharacters(): List<CharacterDTO?>

    @Query("SELECT * FROM CharacterDTO where id = :idCharacter")
    suspend fun getCharacter(idCharacter: Long): CharacterDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterDTO?): Long
}