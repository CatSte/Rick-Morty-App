package no.kristiania.androidprogrammeringeksamen.screens.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import no.kristiania.androidprogrammeringeksamen.data.Character
import no.kristiania.androidprogrammeringeksamen.data.CharacterRepository

class CharacterDetailsViewModel : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter = _selectedCharacter.asStateFlow()

    fun setSelectedCharacter(characterId: Int) {
        viewModelScope.launch {
            _loading.value = true
            _selectedCharacter.value = CharacterRepository.getCharacterById(characterId)
            _loading.value = false
        }
    }
}