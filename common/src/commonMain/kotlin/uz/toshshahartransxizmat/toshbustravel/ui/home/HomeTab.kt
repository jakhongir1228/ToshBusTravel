package uz.toshshahartransxizmat.toshbustravel.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.ui.home.viewModel.HomeViewModel
import uz.toshshahartransxizmat.toshbustravel.util.Other

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
