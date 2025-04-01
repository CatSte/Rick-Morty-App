package no.kristiania.androidprogrammeringeksamen.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import no.kristiania.androidprogrammeringeksamen.data.room.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

object CharacterRepository {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val characterService = retrofit.create(CharacterService::class.java)

    private lateinit var appDatabase: AppDatabase
    private val characterDao by lazy { appDatabase.characterDao() }
    private val userCharacterDao by lazy { appDatabase.userCharacterDao() }

    fun initializeDatabase(context: Context) {
        appDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "character-database"
        ).fallbackToDestructiveMigration().build()
    }

    suspend fun getCharacters(): List<Character> {
        return try {
            val response = characterService.getAllCharacters()
            if (response.isSuccessful) {
                response.body()?.results?.let { characters ->
                    characterDao.insertCharacters(characters)
                    characterDao.getCharacters()
                } ?: emptyList()
            } else {
                Log.e("CharacterRepository", "Failed to fetch characters from API")
                characterDao.getCharacters()
            }
        } catch (e: UnknownHostException) {
            Log.e("CharacterRepository", "No internet connection", e)
            characterDao.getCharacters()
        }
    }

    suspend fun getCharacterById(characterId: Int): Character? {
        return characterDao.getCharacterById(characterId)
    }

    suspend fun createUserCharacter(name: String, species: String, gender: String, imageResId: Int = 0) {
        withContext(Dispatchers.IO) {
            val newCharacter = UserCharacter(
                name = name,
                species = species,
                gender = gender,
                imageResId = imageResId
            )
            userCharacterDao.insertUserCharacter(newCharacter)
        }
    }

    fun getUserCharacters(): Flow<List<UserCharacter>> {
        return userCharacterDao.getUserCharacters()
    }

    fun getUserCharacterById(characterId: Int): Flow<UserCharacter?> {
        return userCharacterDao.getUserCharacterById(characterId)
    }

    suspend fun deleteUserCharacterById(characterId: Int) {
        withContext(Dispatchers.IO) {
            userCharacterDao.deleteUserCharacterById(characterId)
        }
    }
}
