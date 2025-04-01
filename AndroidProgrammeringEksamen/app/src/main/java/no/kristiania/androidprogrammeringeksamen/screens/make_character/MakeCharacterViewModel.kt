package no.kristiania.androidprogrammeringeksamen.screens.make_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import no.kristiania.androidprogrammeringeksamen.data.CharacterRepository
import no.kristiania.pgr208.R

class MakeCharacterViewModel : ViewModel() {

    private val _characterName = MutableStateFlow("")
    val characterName: StateFlow<String> get() = _characterName

    private val _species = MutableStateFlow("")
    val species: StateFlow<String> get() = _species

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> get() = _gender

    private val _selectedImage = MutableStateFlow<Int?>(null)
    val selectedImage: StateFlow<Int?> get() = _selectedImage

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage


    val availableImages = listOf(
        R.drawable.character_1,
        R.drawable.character_2,
        R.drawable.character_3,
        R.drawable.character_4,
        R.drawable.character_5,
        R.drawable.character_6,
        R.drawable.character_7,
        R.drawable.character_8,
        R.drawable.character_9,
        R.drawable.character_10,
        R.drawable.character_11,
        R.drawable.character_12
    )

    fun onCharacterNameChanged(newName: String) {
        _characterName.value = newName
    }

    fun onSpeciesChanged(newSpecies: String) {
        _species.value = newSpecies
    }

    fun onGenderChanged(newGender: String) {
        _gender.value = newGender
    }

    fun onImageSelected(imageRes: Int) {
        _selectedImage.value = imageRes
    }

    private fun validateInputs(): Boolean {
        return when {
            characterName.value.isBlank() -> {
                _errorMessage.value = "Please enter a character name."
                false
            }
            species.value.isBlank() -> {
                _errorMessage.value = "Please enter a species."
                false
            }
            gender.value.isBlank() -> {
                _errorMessage.value = "Please enter a gender."
                false
            }
            selectedImage.value == null -> {
                _errorMessage.value = "Please select a profile image for your character."
                false
            }
            else -> {
                _errorMessage.value = null
                true
            }
        }
    }

    fun createCharacterIfValid(onCharacterCreated: () -> Unit) {
        if (validateInputs()) {
            createCharacter(onCharacterCreated)
        }
    }

    private fun createCharacter(onCharacterCreated: () -> Unit) {
        viewModelScope.launch {
            CharacterRepository.createUserCharacter(
                name = characterName.value,
                species = species.value,
                gender = gender.value,
                imageResId = selectedImage.value ?: 0
            )
            clearCharacterFields()
            onCharacterCreated()
        }
    }

    fun clearCharacterFields() {
        _characterName.value = ""
        _species.value = ""
        _gender.value = ""
        _selectedImage.value = null
    }
}
