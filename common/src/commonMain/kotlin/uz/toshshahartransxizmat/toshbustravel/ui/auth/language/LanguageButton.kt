package uz.toshshahartransxizmat.toshbustravel.ui.auth.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType
import uz.toshshahartransxizmat.toshbustravel.components.button.theme.ButtonRippleTheme
import uz.toshshahartransxizmat.toshbustravel.theme.blue725
import uz.toshshahartransxizmat.toshbustravel.theme.gray300
import uz.toshshahartransxizmat.toshbustravel.theme.gray500
import uz.toshshahartransxizmat.toshbustravel.theme.gray700
import uz.toshshahartransxizmat.toshbustravel.theme.silver100
import uz.toshshahartransxizmat.toshbustravel.theme.silver400
import uz.toshshahartransxizmat.toshbustravel.theme.silver950

@Composable
fun LanguageButton(
    modifier: Modifier = Modifier,
    flagIcon: Painter,
    languageName: String,
    size: ButtonSize = ButtonSize.Large,
    type: ButtonType = ButtonType.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    navigateToAuthScreen: () -> Unit
)= ButtonRippleTheme{
    Button(
        modifier = modifier
            .height(56.dp)
            .border(
                width = 1.dp,
                color = type.borderColor(enabled = !loading && enabled).value,
                shape = size.shape
            )
            .clip(size.shape)
            .background(if (!loading && enabled) Color(0xFFF1F5F9) else Color.Transparent)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, color = Color.Cyan),
                enabled = !loading && enabled,
                onClick = navigateToAuthScreen
            ),
        onClick = { navigateToAuthScreen.invoke() } ,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = flagIcon,
                contentDescription = "lan"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = languageName,
                color = Color(0xFF0F172A)
            )
        }
    }
}