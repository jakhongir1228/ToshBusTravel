package uz.toshshahartransxizmat.toshbustravel.ui.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import uz.toshshahartransxizmat.toshbustravel.domain.model.Orders

@Composable
internal fun showOrderDetailsDialog(order: Orders) {
    AlertDialog(
        onDismissRequest = { /* Handle dismiss */ },
        title = { Text(text = "Order Details") },
        text = {
            Column {
                Text(text = "Model: ${order.modelName}")
                Text(text = "Vehicle Number: ${order.vehicleNumber}")
                Text(text = "Start Date: ${order.startDate}")
                Text(text = "End Date: ${order.endDate}")
                Text(text = "Amount: ${order.amount} UZS")
                Text(text = "Status: ${order.status}")
            }
        },
        confirmButton = {


//            Button(onClick = { /* Handle confirm */ }) {
//                Text("OK")
//            }
        }
    )
}