package no.kristiania.androidprogrammeringeksamen.screens.main_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun MainPageScreen(
    onNavigateToMakeCharacter: () -> Unit,
    onNavigateToCustomCharacters: () -> Unit,
    onNavigateToShowCharacters: () -> Unit,
    onNavigateBackToWelcome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.backdrop_main_page),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.6f), BlendMode.Darken)
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
                IconButton(onClick = { onNavigateBackToWelcome() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Welcome Screen",
                        tint = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Rick and Morty Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(110.dp)
                        .offset(y = 30.dp)
                )

                Spacer(modifier = Modifier.height(48.dp))

                Image(
                    painter = painterResource(id = R.drawable.middlefinger),
                    contentDescription = "Rick and Morty Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp)
                        .height(230.dp)
                        .offset(x = 30.dp)
                )

                Button(
                    onClick = { onNavigateToMakeCharacter() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CB567).copy(alpha = 0.8f)
                    ),
                    modifier = Modifier.size(width = 320.dp, height = 70.dp)
                ) {
                    Text(text = "Create your own character",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(38.dp))

                Button(
                    onClick = { onNavigateToCustomCharacters() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CB567).copy(alpha = 0.8f),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.size(width = 320.dp, height = 70.dp)
                ) {
                    Text(text = "See your custom characters",
                        color = Color.Black,
                                fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(38.dp))

                Button(
                    onClick = { onNavigateToShowCharacters() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CB567).copy(alpha = 0.8f),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.size(width = 320.dp, height = 70.dp)
                ) {
                    Text(text = "See characters from the show",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
