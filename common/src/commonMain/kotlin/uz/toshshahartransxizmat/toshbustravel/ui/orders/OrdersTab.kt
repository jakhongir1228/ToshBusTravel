package uz.toshshahartransxizmat.toshbustravel.ui.orders

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

internal object OrdersTab: Tab {

    @Composable
    override fun Content() {
        Text(
            text = "Orders"
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    override val options: TabOptions

        @Composable
        get() {
            val icon = painterResource("drawable/arrowDownIcon.png")

            return TabOptions(
                index = 1u,
                title = "",
                icon = icon
            )
        }
}
