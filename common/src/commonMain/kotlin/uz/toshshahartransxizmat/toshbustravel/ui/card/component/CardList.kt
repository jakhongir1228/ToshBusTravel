package uz.toshshahartransxizmat.toshbustravel.ui.card.component

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
import uz.toshshahartransxizmat.toshbustravel.domain.model.Cards

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun CardList(
    modifier: Modifier = Modifier,
    list: List<Cards>,
    onClick: (Cards) -> Unit
){
    LazyColumn(
        modifier = modifier
    ){
        items(
            items = list
        ){ card ->
            val cardImg = when (card.type) {
                "UZCARD" -> painterResource("drawable/uzCard.xml")
                "HUMO" -> painterResource("drawable/humoCard.xml")
                "VISA_CARD" -> painterResource("drawable/visaCard.xml")
                "MASTER_CARD" -> painterResource("drawable/masterCard.xml")
                else -> painterResource("drawable/uzCard.xml")
            }

            val cardIcon = when (card.type) {
                "UZCARD" -> painterResource("drawable/iconUzCard.xml")
                "HUMO" -> painterResource("drawable/iconHumo.xml")
                "VISA_CARD" -> painterResource("drawable/iconVisa.xml")
                "MASTER_CARD" -> painterResource("drawable/iconMasterCard.xml")
                else -> painterResource("drawable/iconUzCard.xml")
            }

            CardItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .padding(4.dp)
                    .clickable { onClick(card) },
                card = card,
                cardImage = cardImg,
                cardIcon = cardIcon
            )
        }
    }
}