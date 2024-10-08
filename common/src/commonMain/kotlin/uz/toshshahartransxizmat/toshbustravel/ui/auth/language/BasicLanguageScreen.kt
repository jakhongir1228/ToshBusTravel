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
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BasicLanguageScreen(
    modifier: Modifier = Modifier,
    navigateToAuthScreen: () -> Unit
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
                        text = "Выберите язык",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LanguageButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        painterResource(res = "drawable/UZ.png"),
                        languageName = "O`zbekcha"
                    ){
                        navigateToAuthScreen.invoke()
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    LanguageButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        painterResource(res = "drawable/EN.png"),
                        languageName = "English"
                    ){
                        navigateToAuthScreen.invoke()
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    LanguageButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        painterResource(res = "drawable/RU.png"),
                        languageName = "Русский"
                    ){
                        navigateToAuthScreen.invoke()
                    }
                }

                Box(modifier = Modifier.padding(bottom = 20.dp))
            }
        }
    )
}