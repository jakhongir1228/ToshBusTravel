package uz.toshshahartransxizmat.toshbustravel.ui.orders.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun OrderList(
    modifier: Modifier = Modifier,
    list: List<Orders>,
    onClick: (Orders) -> Unit
){
    LazyColumn(
        modifier = modifier
    ){
        items(
            items = list
        ){ order ->
            val iconOrder = when (order.status) {
                "NEW" -> painterResource("drawable/iconNew.png")
                "PROCESSING" -> painterResource("drawable/iconProcess.png")
                else -> painterResource("drawable/iconNew.png")
            }

            OrderItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .padding(4.dp)
                    .clickable { onClick(order) },
                order = order,
                orderIcon = iconOrder
            )
        }
    }
}