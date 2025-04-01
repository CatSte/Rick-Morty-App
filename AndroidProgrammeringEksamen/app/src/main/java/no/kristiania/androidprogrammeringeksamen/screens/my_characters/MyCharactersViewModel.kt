package no.kristiania.androidprogrammeringeksamen.screens.my_characters

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import no.kristiania.androidprogrammeringeksamen.data.CharacterRepository
import no.kristiania.androidprogrammeringeksamen.data.UserCharacter

class MyCharactersViewModel: ViewModel() {

    fun getUserCharacters(): Flow<List<UserCharacter>> {
        return CharacterRepository.getUserCharacters()
    }

}