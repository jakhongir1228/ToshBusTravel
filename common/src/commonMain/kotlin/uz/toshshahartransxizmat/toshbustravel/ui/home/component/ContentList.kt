package uz.toshshahartransxizmat.toshbustravel.ui.home.component

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
import uz.toshshahartransxizmat.toshbustravel.domain.model.Transports

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ContentList(
    modifier: Modifier = Modifier,
    list: List<Transports>,
    onClick: (Transports) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list
        ) { transport ->
            val iconTransport = when (transport.typeTransport) {
                "MINI" -> painterResource("drawable/iconMini.png")
                "BIG" -> painterResource("drawable/iconBig.png")
                else -> painterResource("drawable/iconMini.png")
            }
            val transportImage = when (transport.typeTransport) {
                "MINI" -> painterResource("drawable/mini.png")
                "BIG" -> painterResource("drawable/big.png")
                else -> painterResource("drawable/mini.png")
            }
            TransportItem(
                transport = transport,
                transportIcon = iconTransport,
                transportImage = transportImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .padding(4.dp)
                    .clickable { onClick(transport) }
            )
        }
    }
}