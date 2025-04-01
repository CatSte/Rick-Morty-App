package no.kristiania.androidprogrammeringeksamen

import WelcomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import no.kristiania.androidprogrammeringeksamen.data.CharacterRepository
import no.kristiania.androidprogrammeringeksamen.screens.character_details.CharacterDetailsScreen
import no.kristiania.androidprogrammeringeksamen.screens.character_details.CharacterDetailsViewModel
import no.kristiania.androidprogrammeringeksamen.screens.character_list.CharacterListScreen
import no.kristiania.androidprogrammeringeksamen.screens.character_list.CharacterListViewModel
import no.kristiania.androidprogrammeringeksamen.screens.make_character.MakeCharacterScreen
import no.kristiania.androidprogrammeringeksamen.screens.make_character.MakeCharacterViewModel
import no.kristiania.androidprogrammeringeksamen.screens.main_page.MainPageScreen
import no.kristiania.androidprogrammeringeksamen.screens.my_characters.MyCharacterListScreen
import no.kristiania.androidprogrammeringeksamen.screens.my_characters.MyCharactersViewModel
import no.kristiania.androidprogrammeringeksamen.screens.my_characters_details.MyCharacterDetailsScreen
import no.kristiania.androidprogrammeringeksamen.ui.theme.AndroidProgrammeringEksamenTheme
import kotlinx.serialization.Serializable

@Serializable
data object Welcome

@Serializable
data object Main

@Serializable
data object MakeCharacter

@Serializable
data object MyCharactersList

@Serializable
data class MyCharactersDetails(val myCharactersId: Int)

@Serializable
data object CharacterList

@Serializable
data class CharacterDetails(val characterId: Int)


class MainActivity : ComponentActivity() {

    private val makeCharacterViewModel: MakeCharacterViewModel by viewModels()
    private val myCharactersViewModel: MyCharactersViewModel by viewModels()
    private val characterListViewModel: CharacterListViewModel by viewModels()
    private val characterDetailsViewModel: CharacterDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CharacterRepository.initializeDatabase(applicationContext)

        setContent {
            AndroidProgrammeringEksamenTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Welcome
                ) {


                    composable<Welcome> {
                        WelcomeScreen(
                            onStartClick = { navController.navigate(Main) }
                        )
                    }


                    composable<Main> {
                        MainPageScreen(
                            onNavigateToMakeCharacter = { navController.navigate(MakeCharacter) },
                            onNavigateToCustomCharacters = { navController.navigate(MyCharactersList) },
                            onNavigateToShowCharacters = { navController.navigate(CharacterList) },
                            onNavigateBackToWelcome = { navController.navigate(Welcome) }
                        )
                    }

                    composable<CharacterList> {
                        CharacterListScreen(
                            viewModel = characterListViewModel,
                            onCharacterClick = { characterId ->
                                navController.navigate(CharacterDetails(characterId))
                            },
                            onNavigateBackToMain = { navController.popBackStack() }
                        )
                    }

                    composable<CharacterDetails> { backStackEntry ->
                        val details = backStackEntry.toRoute<CharacterDetails>()

                        LaunchedEffect(details) {
                            characterDetailsViewModel.setSelectedCharacter(details.characterId)
                        }

                        CharacterDetailsScreen(
                            viewModel = characterDetailsViewModel,
                            onBackButtonClick = { navController.popBackStack() }
                        )
                    }


                    composable<MakeCharacter> {
                        MakeCharacterScreen(
                            viewModel = makeCharacterViewModel,
                            onCharacterCreated = { navController.popBackStack() },
                            onNavigateBackToMain = { navController.popBackStack() },
                            onNavigateToMyCharacters = { navController.navigate(MyCharactersList) }
                        )
                    }


                    composable<MyCharactersList> {
                        MyCharacterListScreen(
                            viewModel = myCharactersViewModel,
                            makeCharacterViewModel = makeCharacterViewModel,
                            onNavigateToMainPage = { navController.navigate(Main) },
                            onNavigateToMakeCharacter = { navController.navigate(MakeCharacter) },
                            onCharacterClick = { customCharacterId ->
                                navController.navigate(MyCharactersDetails(customCharacterId))
                            }
                        )
                    }


                    composable<MyCharactersDetails> { backStackEntry ->
                        val details = backStackEntry.toRoute<MyCharactersDetails>()
                        MyCharacterDetailsScreen(
                            viewModel = viewModel(),
                            characterId = details.myCharactersId,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }

                }
            }
        }
    }
}