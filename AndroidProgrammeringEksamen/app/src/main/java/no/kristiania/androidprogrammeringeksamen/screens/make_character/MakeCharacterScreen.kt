package no.kristiania.androidprogrammeringeksamen.screens.make_character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.kristiania.pgr208.R

@Composable
fun MakeCharacterScreen(
    viewModel: MakeCharacterViewModel,
    onCharacterCreated: () -> Unit = {},
    onNavigateBackToMain: () -> Unit,
    onNavigateToMyCharacters: () -> Unit
) {
    val characterName = viewModel.characterName.collectAsState().value
    val characterSpecies = viewModel.species.collectAsState().value
    val characterGender = viewModel.gender.collectAsState().value
    val selectedImage = viewModel.selectedImage.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.backdrop_makecharacter),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.8f), BlendMode.Darken)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onNavigateBackToMain() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Main Page",
                        tint = Color.White
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Create Your Own Character",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                )
            }


            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(100.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {


                Spacer(modifier = Modifier.height(26.dp))

                TextField(
                    value = characterName,
                    onValueChange = viewModel::onCharacterNameChanged,
                    label = { Text("Character Name",
                        fontSize = 18.sp) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = characterSpecies,
                    onValueChange = viewModel::onSpeciesChanged,
                    label = { Text("Species",
                        fontSize = 18.sp) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = characterGender,
                    onValueChange = viewModel::onGenderChanged,
                    label = { Text("Gender",
                        fontSize = 18.sp) },
                    modifier = Modifier.fillMaxWidth()
                )




                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Scroll to see all images",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(10.dp)
                )


                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    viewModel.availableImages.chunked(2)
                        .forEach { rowImages ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                rowImages.forEach { imageRes ->
                                    val isSelected = imageRes == selectedImage
                                    val backgroundColor = if (isSelected) Color(0xFF4CB567).copy(alpha = 0.6f) else Color.LightGray.copy(alpha = 0.3f)

                                    Box(
                                        modifier = Modifier
                                            .size(170.dp)
                                            .padding(4.dp)
                                            .background(
                                                backgroundColor,
                                                shape = MaterialTheme.shapes.medium
                                            )
                                            .clickable { viewModel.onImageSelected(imageRes) },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = imageRes),
                                            contentDescription = null,
                                            modifier = Modifier.size(140.dp)
                                        )
                                    }
                                }
                            }
                        }

                    Spacer(modifier = Modifier.height(16.dp))


                    errorMessage?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.createCharacterIfValid {
                                onCharacterCreated()
                                onNavigateToMyCharacters()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CB567).copy(alpha = 0.8f),
                            contentColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(68.dp)
                    ) {
                        Text(
                            "Create Character",
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}