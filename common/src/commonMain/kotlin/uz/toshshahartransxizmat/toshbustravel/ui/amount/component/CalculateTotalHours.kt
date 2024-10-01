package uz.toshshahartransxizmat.toshbustravel.ui.amount.component

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

@Composable
fun calculateTotalHours(startDate: String, startTime: String, endDate: String, endTime: String): Long {
    val start = parseLocalDateTime(startDate, startTime)
    val end = parseLocalDateTime(endDate, endTime)

    val startInstant = start.toInstant(TimeZone.currentSystemDefault())
    val endInstant = end.toInstant(TimeZone.currentSystemDefault())

    val duration = endInstant - startInstant
    return duration.inWholeHours
}

fun parseLocalDateTime(date: String, time: String): LocalDateTime {
    val paddedTime = time.split(":").map { it.padStart(2, '0') }
    val formattedTime = "${paddedTime[0]}:${paddedTime[1]}:00"
    return LocalDateTime.parse("${date}T$formattedTime")
}