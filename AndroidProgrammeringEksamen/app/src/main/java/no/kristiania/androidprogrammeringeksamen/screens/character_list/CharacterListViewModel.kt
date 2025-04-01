package no.kristiania.androidprogrammeringeksamen.screens.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import no.kristiania.androidprogrammeringeksamen.data.Character
import no.kristiania.androidprogrammeringeksamen.data.CharacterRepository


class CharacterListViewModel : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters = _characters.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            _characters.value = CharacterRepository.getCharacters()
            _loading.value = false
        }
    }

}