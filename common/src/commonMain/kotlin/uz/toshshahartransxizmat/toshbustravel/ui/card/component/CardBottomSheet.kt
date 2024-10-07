package uz.toshshahartransxizmat.toshbustravel.ui.card.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.util.getStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CardBottomSheet(
    title: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        modifier = Modifier
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                onDismissRequest()
            },
        scaffoldState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    text = TextValue(text = title),
                    color = MaterialTheme.colorScheme.scrim.copy( )
                )
                Spacer(modifier = Modifier.height(8.dp))

                CardMessageComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    text = getStrings("active_card_tip")
                )
            }
        },
        sheetContainerColor = Color.White
    ) {
        scope.launch {
            bottomSheetState.bottomSheetState.expand()
        }
    }
}