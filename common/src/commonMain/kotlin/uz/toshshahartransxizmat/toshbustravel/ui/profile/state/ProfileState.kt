package uz.toshshahartransxizmat.toshbustravel.ui.profile.state

import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientData
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.ClientUpdateData

data class ProfileState(
    val success: ClientData = ClientData(0, "","",null),
    val successUpdate: ClientUpdateData = ClientUpdateData(null,null,false),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)
