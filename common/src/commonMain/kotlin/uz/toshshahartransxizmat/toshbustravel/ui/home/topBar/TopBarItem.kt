package uz.toshshahartransxizmat.toshbustravel.ui.home.topBar

sealed class TopBarItem(
    val label: String
) {
    object General: TopBarItem(
        label = "General"
    )
    object Business: TopBarItem(
        label = "Businessrrrrr"
    )
    object Traveling: TopBarItem(
        label = "Technologyrrrrrr"
    )
}