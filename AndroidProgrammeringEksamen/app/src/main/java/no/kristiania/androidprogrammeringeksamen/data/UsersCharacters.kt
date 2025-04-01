package no.kristiania.androidprogrammeringeksamen.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_characters")
data class UserCharacter(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val name: String,
    val species: String,
    val gender: String,
    val imageResId: Int
)
