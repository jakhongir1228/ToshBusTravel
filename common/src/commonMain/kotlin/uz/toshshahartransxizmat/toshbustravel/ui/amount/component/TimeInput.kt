package uz.toshshahartransxizmat.toshbustravel.ui.amount.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Timer10Select
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TimeInput(
    title: String,
    modifier: Modifier = Modifier,
    onTimeChange: (String) -> Unit
){
    var showDialog by remember { mutableStateOf(false) }
    var formatTime by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ){
        if (showDialog) {
            AdvancedTimePicker(
                onConfirm = { timePickerState ->
                    val time = "${timePickerState.hour}:${timePickerState.minute}"
                    onTimeChange(time)
                    formatTime = time
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }

        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier
                .height(52.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable { showDialog = true }
            ) {
                Text(
                    text = formatTime.ifEmpty { "HH:MM" },
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (formatTime.isEmpty()) Color.Gray else LocalContentColor.current,
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(24.dp)
                    .clickable { showDialog = true },
                imageVector = Icons.Default.Timer,
                contentDescription = "Open calendar",
            )
        }

    }
}