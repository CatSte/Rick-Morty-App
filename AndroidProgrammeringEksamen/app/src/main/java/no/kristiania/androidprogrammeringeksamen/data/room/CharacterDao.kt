package no.kristiania.androidprogrammeringeksamen.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.kristiania.androidprogrammeringeksamen.data.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM `Character`")
    suspend fun getCharacters(): List<Character>

    @Query("SELECT * FROM `Character` WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): Character?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Character>)
}

