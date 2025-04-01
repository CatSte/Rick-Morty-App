package no.kristiania.androidprogrammeringeksamen.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import no.kristiania.androidprogrammeringeksamen.data.UserCharacter

@Dao
interface UserCharacterDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertUserCharacter(character: UserCharacter)

        @Query("SELECT * FROM user_characters")
        fun getUserCharacters(): Flow<List<UserCharacter>>

        @Query("SELECT * FROM user_characters WHERE id = :characterId")
        fun getUserCharacterById(characterId: Int): Flow<UserCharacter?>

        @Query("DELETE FROM user_characters WHERE id = :characterId")
        suspend fun deleteUserCharacterById(characterId: Int)

}