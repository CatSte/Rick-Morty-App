package no.kristiania.androidprogrammeringeksamen.screens.my_characters_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import no.kristiania.androidprogrammeringeksamen.data.UserCharacter
import no.kristiania.androidprogrammeringeksamen.data.CharacterRepository
import androidx.lifecycle.viewModelScope

class MyCharactersDetailsViewModel : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    fun getCharacterById(characterId: Int): Flow<UserCharacter?> {
        _loading.value = true
        return CharacterRepository.getUserCharacterById(characterId).also { flow ->
            viewModelScope.launch {
                flow.collect {
                    _loading.value = false
                }
            }
        }
    }

    fun deleteCharacterById(characterId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            CharacterRepository.deleteUserCharacterById(characterId)
            onSuccess()
        }
    }

}
