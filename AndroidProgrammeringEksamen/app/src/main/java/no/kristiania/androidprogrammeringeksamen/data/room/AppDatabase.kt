package no.kristiania.androidprogrammeringeksamen.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import no.kristiania.androidprogrammeringeksamen.data.Character
import no.kristiania.androidprogrammeringeksamen.data.UserCharacter

@Database(
    entities = [Character::class, UserCharacter::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(CharacterTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun userCharacterDao(): UserCharacterDao
}
