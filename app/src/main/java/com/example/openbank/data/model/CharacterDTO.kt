package com.example.openbank.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Axel Sanchez
 */
@Entity
data class CharacterDTO(
    @PrimaryKey(autoGenerate = true) var id: Long,
    val description: String,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail
    //val comics: Comics,
    //val events: Events,
    //val series: Series,
    //val stories: Stories,
    //val urls: List<Url>
)