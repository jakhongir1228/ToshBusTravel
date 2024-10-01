package uz.toshshahartransxizmat.toshbustravel.ui.amount.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
internal fun DateInput(
    title: String,
    modifier: Modifier = Modifier,
    onDateChange: (String) -> Unit
) {
    var selectedDate by remember { mutableStateOf<Instant?>(null) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var formattedDate by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        if (showDatePickerDialog) {
            DatePickerModal(
                onDateSelected = { date ->
                    selectedDate = date?.let { Instant.fromEpochMilliseconds(it) }
                    if (selectedDate != null) {
                        val localDateTime = selectedDate!!.toLocalDateTime(TimeZone.currentSystemDefault())
                        formattedDate = localDateTime.date.toString()
                        onDateChange(formattedDate)
                    }
                    showDatePickerDialog = false
                },
                onDismiss = {
                    showDatePickerDialog = false
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
                    .clickable { showDatePickerDialog = true }
            ) {
                Text(
                    text = formattedDate.ifEmpty { "YYYY-MM-DD" },
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (formattedDate.isEmpty()) Color.Gray else LocalContentColor.current,
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(24.dp)
                    .clickable { showDatePickerDialog = true },
                imageVector = Icons.Default.EditCalendar,
                contentDescription = "Open calendar",
            )
        }
    }
}
