package uz.toshshahartransxizmat.toshbustravel.ui.auth.language

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.util.LanguageManager
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BasicLanguageScreen(
    modifier: Modifier = Modifier,
    navigateToAuthScreen: (String) -> Unit
) {
    Scaffold(
        content = {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val alpha = remember { Animatable(0f) }

                LaunchedEffect(Unit) {
                    alpha.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 1000)
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(res = "drawable/logo.png"),
                    contentDescription = "App Logo",
                    modifier = Modifier.alpha(alpha.value)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        text = getStrings("select_language"),
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LanguageButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        painterResource(res = "drawable/UZ.png"),
                        languageName = "O`zbekcha"
                    ){
                        LanguageManager.setLanguage("uz")
                        navigateToAuthScreen.invoke("uz")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LanguageButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        painterResource(res = "drawable/EN.png"),
                        languageName = "English"
                    ){
                        LanguageManager.setLanguage("en")
                        navigateToAuthScreen.invoke("en")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LanguageButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        painterResource(res = "drawable/RU.png"),
                        languageName = "Русский"
                    ){
                        LanguageManager.setLanguage("ru")
                        navigateToAuthScreen.invoke("ru")
                    }
                }

                Box(modifier = Modifier.padding(bottom = 20.dp))
            }
        }
    )
}