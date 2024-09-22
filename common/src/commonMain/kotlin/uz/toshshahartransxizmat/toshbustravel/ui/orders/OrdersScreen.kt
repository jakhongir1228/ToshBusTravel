package uz.toshshahartransxizmat.toshbustravel.ui.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.toshshahartransxizmat.toshbustravel.ui.orders.component.OrderItem
import uz.toshshahartransxizmat.toshbustravel.ui.orders.component.OrderList
import uz.toshshahartransxizmat.toshbustravel.ui.orders.state.OrderState
import uz.toshshahartransxizmat.toshbustravel.ui.profile.component.showOrderDetailsDialog
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

internal class OrdersScreen(
    private val state: State<OrderState>,
    private val loadGetOrders: () -> Unit
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            loadGetOrders.invoke()
        }

        Scaffold {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(bottom = 72.dp)
            ) {
                Text(
                    text = getStrings("your_orders"),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                val orders = state.value.orders

                OrderList(
                    list = orders,
                    onClick = { order ->

                    }
                )
            }
        }
    }
}
