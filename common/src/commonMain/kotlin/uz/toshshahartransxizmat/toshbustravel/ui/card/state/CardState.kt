package uz.toshshahartransxizmat.toshbustravel.ui.card.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.Cards
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AddCardData

data class CardState(
    val cards: List<Cards> = emptyList(),
    val addCardData: AddCardData = AddCardData(
        cardToken = "",
        smsNotificationNumber = null
    ),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
