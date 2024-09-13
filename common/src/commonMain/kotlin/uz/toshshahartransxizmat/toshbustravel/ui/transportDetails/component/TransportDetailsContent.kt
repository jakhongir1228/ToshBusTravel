package uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.TransportDetailsData
import uz.toshshahartransxizmat.toshbustravel.theme.errorLight
import uz.toshshahartransxizmat.toshbustravel.theme.grayA250
import uz.toshshahartransxizmat.toshbustravel.theme.white100
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TransportDetailsContent(
    transportDetailsData: TransportDetailsData,
    navigator: Navigator,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(white100, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Text(
                        text = transportDetailsData.modelName,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .width(130.dp)
                            .border(
                                BorderStroke(1.dp, grayA250),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Column {
                            Text(
                                text = getStrings("number_of_seats"),
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = transportDetailsData.passengerCapacity.toString()+" "+ getStrings("seats"),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = getStrings("amenities"),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Column {
                        transportDetailsData.vehicleDetails.forEach { detail ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(when(detail.iconPath) {
                                        "MONITOR_TYPE" -> "drawable/iconMonitor.png"
                                        "USB_TYPE" -> "drawable/iconUsb.png"
                                        "CONTIDIONER_TYPE" -> "drawable/iconConditioner.png"
                                        else -> "drawable/iconConditioner.png"
                                    }),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp).padding(end = 8.dp)
                                )
                                Text(
                                    text = detail.name,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = getStrings("booked"),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Column {
                        transportDetailsData.orderCharts.forEach { order ->
                            Text(
                                text = "${order.orderStart}  до  ${order.orderEnd}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = errorLight
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                text = TextValue("Продолжить"),
                onClick = {
                    //  navigator.push(ApplyForScreen())
                },
            )
        }
    }
}