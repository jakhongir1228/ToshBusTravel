package uz.toshshahartransxizmat.toshbustravel.ui.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.ui.orders.component.OrderItem
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal object OrdersTab: Tab {

    @Composable
    override fun Content() {

        Scaffold {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(bottom = 72.dp)
            ){
                Text(
                    text = getStrings("your_orders"),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                )
                // Order list items
                OrderItem(status = "В ожидании", statusColor = Color(0xFFFFE3B3))
                Spacer(modifier = Modifier.height(8.dp))
                OrderItem(status = "Успешно", statusColor = Color(0xFFDFF5E1))
            }
        }
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
