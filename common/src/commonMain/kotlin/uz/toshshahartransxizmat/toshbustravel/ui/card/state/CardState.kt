package uz.toshshahartransxizmat.toshbustravel.ui.card.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.Cards
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AddCardData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.VerifyCardData

data class CardState(
    val cards: List<Cards> = emptyList(),
    val addCardData: AddCardData = AddCardData(
        cardToken = "",
        smsNotificationNumber = null
    ),
    val verifyCardData: VerifyCardData = VerifyCardData(
        token = "", cardNumber = "", balance = 0, expiryDate = "", externalToken = "", smsNotificationNumber = null, type = null, holderFullName = "", externalStatus = null, bankName = "", cvv = null
    ),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
