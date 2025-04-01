package no.kristiania.androidprogrammeringeksamen.screens.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import no.kristiania.androidprogrammeringeksamen.data.Character
import no.kristiania.androidprogrammeringeksamen.data.Origin


@Composable
fun CharacterItem(
    character: Character,
    onClick: () -> Unit = {}
) {

    val itemWidth = 170.dp
    val itemHeight = 200.dp

    Column(
        modifier = Modifier
            .size(width = itemWidth, height = itemHeight)
            .padding(8.dp)
            .background(Color.DarkGray.copy(alpha = 0.7f), shape = RoundedCornerShape(10))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Image of ${character.name}",
            modifier = Modifier
                .size(120.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${character.id}. ${character.name}",
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

@Preview(showBackground = true)
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        character = Character(
            id = 110,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "Human",
            gender = "Male",
            origin = Origin("Earth", "https://rickandmortyapi.com/api/location/1"),
            image = "https://cdn.dribbble.com/users/6523406/screenshots/14702237/sahar_heumesser-rick_sanchez-dribbble.png",
        )
    )
}
