import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.kristiania.pgr208.R

@Composable
fun WelcomeScreen(
    onStartClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.backdrop_welcome_page),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(translationX = -30f,
                    scaleX = 1.1f,
                    scaleY = 1.1f)
                .background(Color(0xFF121212)),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color(0xFF121212).copy(alpha = 0.7f), BlendMode.Darken)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.portal),
                    contentDescription = "Rick and Morty Portal",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(320.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Rick and Morty Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(100.dp)
                )

                Spacer(modifier = Modifier.height(36.dp))

                Text(
                    text = "Welcome to the Rick and Morty app!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = "Create and manage your favourite characters!",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = onStartClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CB567).copy(alpha = 0.8f),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .size(width = 250.dp, height = 70.dp)
                ) {
                    Text("Get Started", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
