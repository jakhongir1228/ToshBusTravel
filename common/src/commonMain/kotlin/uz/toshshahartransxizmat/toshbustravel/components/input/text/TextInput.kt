package uz.toshshahartransxizmat.toshbustravel.components.input.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInputDefaultSize.MinHeight
import uz.toshshahartransxizmat.toshbustravel.components.input.text.TextInputDefaultSize.MinWidth
import uz.toshshahartransxizmat.toshbustravel.theme.black50
import uz.toshshahartransxizmat.toshbustravel.theme.silver200
import uz.toshshahartransxizmat.toshbustravel.theme.silver950
import uz.toshshahartransxizmat.toshbustravel.theme.white100
import uz.toshshahartransxizmat.toshbustravel.theme.white70

@Composable
fun TextInput(
    model: TextInputModel,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    primaryIcon: TextInputIcon? = null,
    secondaryIcon: TextInputIcon? = null,
    maxLines: Int = if (model.singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null
) = TextInput(
    value = model.value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = model.enabled,
    readOnly = model.readOnly,
    label = model.label,
    placeholder = model.placeholder,
    caption = model.caption,
    primaryIcon = primaryIcon,
    secondaryIcon = secondaryIcon,
    isError = model.isError,
    loading = model.loading,
    visualTransformation = model.visualTransformation,
    keyboardOptions = model.keyboardOptions,
    keyboardActions = model.keyboardActions,
    singleLine = model.singleLine,
    maxLines = maxLines,
    minLines = minLines,
    onTextLayout = onTextLayout,
    onClick = onClick
)

@Suppress("LongMethod")
@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: TextValue? = null,
    placeholder: TextValue? = null,
    caption: TextValue? = null,
    primaryIcon: TextInputIcon? = null,
    secondaryIcon: TextInputIcon? = null,
    isError: Boolean = false,
    loading: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null
) {
    val colors = remember { TextInputColors() }
    val interactionSource = remember { MutableInteractionSource() }

    val alphaModifier = if (!enabled) {
        Modifier.alpha(alpha = 0.5f)
    } else {
        Modifier
    }

    val clickableModifier = if (readOnly && onClick != null) {
        Modifier.clickable(
            onClick = onClick,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        )
    } else {
        Modifier
    }

    Column(
        modifier = Modifier
            .then(alphaModifier)
            .defaultMinSize(MinWidth, MinHeight)
            .then(modifier)
    ) {
        if (label != null) {
            Text(
                modifier = Modifier
                    .defaultMinSize(minWidth = MinWidth)
                    .padding(bottom = 8.dp),
                text = label,
                color = colors.labelColor(isError).value
            )
        }
        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            enabled = enabled && !loading,
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(Color.Black),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = colors.borderColor(
                                readOnly = readOnly,
                                isError = isError,
                                interactionSource = interactionSource
                            ).value,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .then(clickableModifier)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(weight = 1f)
                            .padding(vertical = 10.dp),
                    ) {
                        if (placeholder != null && value.isBlank()) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 2.dp),
                                text = placeholder,
                                color = colors.placeholderColor(isError).value
                            )
                        }
                        innerTextField()
                    }
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 2.dp)
                                .size(18.dp),
                            color = white100,
                            strokeWidth = 1.dp
                        )
                    } else {
                        secondaryIcon?.content(
                            enabled = enabled,
                            readOnly = readOnly,
                            onValueChange = onValueChange
                        )
                        primaryIcon?.content(
                            enabled = enabled,
                            readOnly = readOnly,
                            onValueChange = onValueChange
                        )
                    }
                }
            }
        )
        if (caption != null) {
            Text(
                modifier = Modifier
                    .defaultMinSize(minWidth = MinWidth)
                    .padding(top = 8.dp),
                text = caption,
                color = colors.captionColor(isError).value
            )
        }
    }
}
