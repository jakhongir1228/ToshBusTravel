package uz.toshshahartransxizmat.toshbustravel.ui.auth.language

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.toshshahartransxizmat.toshbustravel.ui.auth.LogInScreen


internal class LanguageScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        BasicLanguageScreen(
            modifier = Modifier,
            navigateToAuthScreen = {
                navigator.push(LogInScreen())
            }
        )
    }
}
