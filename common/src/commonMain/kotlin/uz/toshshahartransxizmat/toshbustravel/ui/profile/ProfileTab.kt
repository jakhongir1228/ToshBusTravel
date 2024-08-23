package uz.toshshahartransxizmat.toshbustravel.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

internal object ProfileTab: Tab {

    @Composable
    override fun Content() {
        Text(
            text = "Profile"
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