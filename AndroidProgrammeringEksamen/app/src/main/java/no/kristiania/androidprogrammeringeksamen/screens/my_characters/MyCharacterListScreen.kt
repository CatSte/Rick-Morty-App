package no.kristiania.androidprogrammeringeksamen.screens.my_characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.kristiania.androidprogrammeringeksamen.screens.make_character.MakeCharacterViewModel
import no.kristiania.pgr208.R


@Composable
fun MyCharacterListScreen(
    viewModel: MyCharactersViewModel,
    makeCharacterViewModel: MakeCharacterViewModel,
    onNavigateToMainPage: () -> Unit,
    onNavigateToMakeCharacter: () -> Unit,
    onCharacterClick: (Int) -> Unit
) {
    val userCharacters = viewModel.getUserCharacters().collectAsState(initial = emptyList()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backdrop_mycharacters),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.7f), BlendMode.Darken)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onNavigateToMainPage() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Main Screen",
                        tint = Color.White
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(end = 40.dp)
                        .fillMaxWidth(),
                    text = "Your Characters",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Rick and Morty Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .padding(vertical = 10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        makeCharacterViewModel.clearCharacterFields()
                        onNavigateToMakeCharacter()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CB567),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .size(width = 250.dp, height = 60.dp)
                ) {
                    Text(
                        text = "Make New Character",
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) {
                items(userCharacters) { myCharacter ->
                    MyCharacterItem(
                        myCharacter = myCharacter,
                        onClick = { onCharacterClick(myCharacter.id) }
                    )
                }
            }
        }
    }
}

