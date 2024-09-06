package uz.toshshahartransxizmat.toshbustravel.ui.home.topBar

sealed class TopBarItem(
    val label: String
) {
    object All: TopBarItem(
        label = "Все"
    )
    object  Bus: TopBarItem(
        label = "Микроавтобус"
    )
    object MiniBus: TopBarItem(
        label = "Автобус"
    )
}