package no.kristiania.androidprogrammeringeksamen.screens.my_characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import no.kristiania.androidprogrammeringeksamen.data.UserCharacter


@Composable
fun MyCharacterItem(
    myCharacter: UserCharacter,
    onClick: () -> Unit
) {
    val itemWidth = 170.dp
    val itemHeight = 200.dp

    Column(
        modifier = Modifier
            .size(width = itemWidth, height = itemHeight)
            .padding(8.dp)
            .background(Color.DarkGray.copy(alpha = 0.7f), shape = MaterialTheme.shapes.medium)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = myCharacter.imageResId,
            contentDescription = "Image of ${myCharacter.name}",
            modifier = Modifier
                .size(120.dp)
                .background(Color.White, shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = myCharacter.name,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}
