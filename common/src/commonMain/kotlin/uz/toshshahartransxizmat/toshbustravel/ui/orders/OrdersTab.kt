package uz.toshshahartransxizmat.toshbustravel.ui.orders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.rememberKoinInject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CreateOrderEntity
import uz.toshshahartransxizmat.toshbustravel.ui.orders.viewModel.OrderViewModel
import uz.toshshahartransxizmat.toshbustravel.util.Other

internal object OrdersTab: Tab {

    @Composable
    override fun Content() {

        val viewModel = rememberKoinInject<OrderViewModel>()
        val state = viewModel.state.collectAsState()

//        val createOrderEntity = CreateOrderEntity(
//            vehicleId = 3,
//            directionsFrom = "test",
//            directionsTo = "lol",
//            price = 15156,
//            orderStatus = "NEW",
//            aLongitude = "40.712776",
//            aLatitude = "-74.005974",
//            bLongitude = "40.730610",
//            bLatitude = "-73.935242",
//            orderDate = "2024-09-04",
//            orderStarts = "2024-09-04T16:20:13.869327600",
//            orderEnds = "2024-09-04T18:20:13.869327600",
//            from = "123 Main St, New York, NY",
//            to = "456 Oak St, Brooklyn, NY",
//            distance = 155,
//            travelTime = 1454
//        )
//
//        LaunchedEffect(Unit) {
//            viewModel.loadCreateOrder(createOrderEntity)
//        }
//
//        if (state.value.isLoaded){
//
//        }

        Navigator(
            OrdersScreen(
                state = state,
                loadGetOrders = viewModel::loadGetOrder
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
            val icon = painterResource("drawable/arrowDownIcon.png")

            return TabOptions(
                index = 1u,
                title = "",
                icon = icon
            )
        }
}
