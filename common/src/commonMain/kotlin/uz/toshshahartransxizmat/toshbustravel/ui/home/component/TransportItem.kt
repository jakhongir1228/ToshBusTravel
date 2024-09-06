package uz.toshshahartransxizmat.toshbustravel.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.Icon
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.icon.IconValue
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports
import uz.toshshahartransxizmat.toshbustravel.theme.black100
import uz.toshshahartransxizmat.toshbustravel.theme.errorLight
import uz.toshshahartransxizmat.toshbustravel.theme.gray650
import uz.toshshahartransxizmat.toshbustravel.theme.white100
import uz.toshshahartransxizmat.toshbustravel.theme.yellowLight

@Composable
fun TransportItem(
    transport: Transports,
    transportIcon: Painter,
    transportImage: Painter,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .background(color = white100, shape = RoundedCornerShape(12.dp))
            .padding(10.dp)
    ){
        Row {
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Image(
                    painter = transportIcon,
                    contentDescription = "Transport Icon",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(48.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = transport.modelName,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            // Transport image
            Image(
                painter = transportImage,
                contentDescription = "Transport Image",
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${transport.passengerCapacity} мест",
                color = gray650
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                icon = IconValue(Icons.Default.Star),
                contentDescription = "Rating Star",
                tint = yellowLight
            )

            Text(
                text = "4.9" ?: "N/A"
            )
        }
    }


}
