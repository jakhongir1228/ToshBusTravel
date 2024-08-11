package uz.toshshahartransxizmat.toshbustravel.ui.orders

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

internal object OrdersTab: Tab {

    @Composable
    override fun Content() {
        Text(
            text = "Orders"
        )
    }

    override val options: TabOptions

        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.HomeWork)

            return TabOptions(
                index = 1u,
                title = "Orders",
                icon = icon
            )
        }
}
