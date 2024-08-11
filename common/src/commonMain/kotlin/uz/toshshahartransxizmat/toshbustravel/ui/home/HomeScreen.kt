package uz.toshshahartransxizmat.toshbustravel.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

internal object HomeScreen: Screen {

    @Composable
    override fun Content() {
        Scaffold {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(bottom = 80.dp)
            ) {
                Text(
                    text = "$$$$$"
                )
            }
        }
    }
}
