package uz.toshshahartransxizmat.toshbustravel.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

internal object ProfileTab: Tab {

    @Composable
    override fun Content() {
        Text(
            text = "Profile"
        )
    }

    override val options: TabOptions

        @Composable
        get() {
            val icon= rememberVectorPainter(Icons.Default.Settings)

            return TabOptions(
                index = 2u,
                title = "Profile",
                icon = icon
            )
        }
}