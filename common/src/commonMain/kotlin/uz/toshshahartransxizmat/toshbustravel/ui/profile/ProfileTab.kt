package uz.toshshahartransxizmat.toshbustravel.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.ui.profile.viewModel.ProfileViewModel
import uz.toshshahartransxizmat.toshbustravel.util.Other

internal object ProfileTab: Tab {

    @Composable
    override fun Content() {
        val viewModel = rememberKoinInject<ProfileViewModel>()
        val state = viewModel.state.collectAsState()

        Navigator(
            ProfileScreen(
                state = state,
                loadGetClient = viewModel::loadGetClient
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
            val icon= painterResource("drawable/profileIcon.png")

            return TabOptions(
                index = 2u,
                title = "",
                icon = icon
            )
        }
}