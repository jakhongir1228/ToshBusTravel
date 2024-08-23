package uz.toshshahartransxizmat.toshbustravel.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LocationInputDialog(
    onDismissRequest: () -> Unit,
    onAddressSelected: (String) -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(Modifier.padding(16.dp)) {
            var address by remember { mutableStateOf("") }

            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Enter Address") }
            )
            Button(onClick = { onAddressSelected(address) }) {
                Text("Select Address")
            }
        }
    }
}
