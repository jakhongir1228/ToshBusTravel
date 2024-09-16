package uz.toshshahartransxizmat.toshbustravel.ui.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import uz.toshshahartransxizmat.toshbustravel.theme.white100

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ProfileCardItem(
    name: String,
    iconUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(res = iconUrl),
                contentDescription = "Profile Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .background(white100, CircleShape)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = name,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {

                }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Edit Icon",
                    tint = Color.Gray
                )
            }
        }
    }
}
