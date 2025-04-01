package no.kristiania.androidprogrammeringeksamen.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import no.kristiania.androidprogrammeringeksamen.data.room.CharacterTypeConverter

@Entity
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String?,
    val gender: String,
    @TypeConverters(CharacterTypeConverter::class)
    val origin: Origin,
    val image: String
)

data class Origin(
    val name: String,
    val url: String
)

