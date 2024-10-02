package uz.toshshahartransxizmat.toshbustravel.ui.orders.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders
import uz.toshshahartransxizmat.toshbustravel.theme.gray500
import uz.toshshahartransxizmat.toshbustravel.theme.white100

@Composable
internal fun OrderItem(
    order: Orders,
    orderIcon: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = white100, shape = RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Color(0xFFE8E8E8), shape = RoundedCornerShape(12.dp))
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = orderIcon,
                    contentDescription = null,
                    tint = gray500
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Заявка  ${order.modelName}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = order.startDate,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Column (
                modifier = Modifier.weight(0.5f)
            ){
                Box(
                    modifier = Modifier
                        .background(getStatusColor(order.status), shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.End)
                ) {
                    Text(
                        text = order.status,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${order.amount} UZS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }


    }
}

fun getStatusColor(status: String): Color {
    return when (status) {
        "NEW" -> Color(0xFFFFE3B3)
        "PROCESSING" -> Color(0xFFDFF5E1)
        else -> Color.Gray
    }
}