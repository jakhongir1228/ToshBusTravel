package uz.toshshahartransxizmat.toshbustravel.ui.home.topBar

sealed class TopBarItem(
    val label: String
) {
    object General: TopBarItem(
        label = "Все"
    )
    object  Business: TopBarItem(
        label = "Микроавтобус"
    )
    object Traveling: TopBarItem(
        label = "Автобус"
    )
}