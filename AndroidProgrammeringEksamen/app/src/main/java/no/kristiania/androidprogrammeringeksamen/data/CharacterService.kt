package no.kristiania.androidprogrammeringeksamen.data

import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {
    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterResponse>
}
