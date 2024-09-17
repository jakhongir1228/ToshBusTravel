package uz.toshshahartransxizmat.toshbustravel.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.ui.home.component.BannerComponent
import uz.toshshahartransxizmat.toshbustravel.ui.home.viewModel.HomeViewModel
import uz.toshshahartransxizmat.toshbustravel.util.Other
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal object HomeTab: Tab {

    @Composable
    override fun Content() {

        val vm = rememberKoinInject<HomeViewModel>()
        val state = vm.state.collectAsState()

        Navigator(
            BasicHomeScreen(
                state = state,
                loadTransports = vm::loadTransports
            ),
            onBackPressed = {
                Other.isBottomBarVisible = true
                true
            }
        )

    }

    @OptIn(ExperimentalResourceApi::class)
    override val options: TabOptions

        @Composable
        get() {
            val icon = painterResource("drawable/homeIcon.png")

            return TabOptions(
                index = 0u,
                title = "",
                icon = icon
            )
        }
}
