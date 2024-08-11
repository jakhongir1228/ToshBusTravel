package uz.toshshahartransxizmat.toshbustravel.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

internal object HomeTab: Tab {

    @Composable
    override fun Content() {
        Navigator(
            HomeScreen
        )
    }

    override val options: TabOptions

        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Home)

            return TabOptions(
                index = 0u,
                title = "Home",
                icon = icon
            )
        }
}
