package com.example.openbank.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.openbank.data.model.CharacterDTO

/**
 * Base de datos utilizando room
 * @author Axel Sanchez
 */
@Database(
    entities = [CharacterDTO::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}