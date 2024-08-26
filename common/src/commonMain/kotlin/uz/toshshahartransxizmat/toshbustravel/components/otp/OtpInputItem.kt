package uz.toshshahartransxizmat.toshbustravel.components.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.KeyboardType.Companion.Number
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.toshshahartransxizmat.toshbustravel.components.button.Button
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonSize
import uz.toshshahartransxizmat.toshbustravel.components.button.ButtonType
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.Text
import uz.toshshahartransxizmat.toshbustravel.components.faoundation.text.TextValue
import uz.toshshahartransxizmat.toshbustravel.theme.blueA220
import uz.toshshahartransxizmat.toshbustravel.theme.gray650
import uz.toshshahartransxizmat.toshbustravel.theme.gray900
import uz.toshshahartransxizmat.toshbustravel.theme.red500
import uz.toshshahartransxizmat.toshbustravel.theme.silver500
import uz.toshshahartransxizmat.toshbustravel.theme.white100

private const val DEFAULT_LENGTH = 6

@Suppress("MagicNumber", "LongMethod", "ModifierNotUsedAtRoot") // input value container
@Composable
internal fun OtpInputItem(
    text: String,
    onTextChanged: (String, Boolean) -> Unit,
    enabled: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier,
    length: Int = DEFAULT_LENGTH
) {
    var internalText by remember { mutableStateOf(text) }
    val clipboardManager = LocalClipboardManager.current
    var showPasteButton by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier) {
        BasicTextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) showPasteButton = false
                },
            value = TextFieldValue(text, selection = TextRange(text.length)),
            onValueChange = {
                val value = it.text.filter(Char::isDigit)
                if (value.length <= length) {
                    internalText = value
                    onTextChanged(value, value.length == length)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = Number, imeAction = Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            decorationBox = { _ ->
                Row(horizontalArrangement = Arrangement.Center) {
                    val isOddLength = length % 2 == 1
                    repeat(length) { index ->
                        val middle = (length / 2) - 1

                        val paddingEnd = when {
                            isOddLength -> 2.dp
                            index < middle -> 2.dp
                            index == middle -> 4.dp
                            else -> 0.dp
                        }

                        val paddingStart = when {
                            isOddLength -> 2.dp
                            index == middle + 1 -> 4.dp
                            index > middle -> 2.dp
                            else -> 0.dp
                        }

                        CharContainer(
                            modifier = Modifier
                                .padding(start = paddingStart, end = paddingEnd)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onLongPress = {
                                            if (!enabled) {
                                                showPasteButton = true
                                            }
                                        },
                                        onTap = {
                                            showPasteButton = false
                                            focusRequester.requestFocus()
                                        }
                                    )
                                },
                            index = index,
                            text = text,
                            otpError = isError
                        )
                    }
                }
            }
        )

        if (showPasteButton) {
            Button(
                modifier = Modifier.align(Alignment.TopStart)
                    .padding(top = 10.dp),
                text = TextValue("Вставить"),
                size = ButtonSize.Medium,
                type = ButtonType.White,
                onClick = {
                    val clipboardText =
                        clipboardManager.getText()?.text?.filter(Char::isDigit) ?: ""
                    if (clipboardText.isNotEmpty() && clipboardText.length <= length) {
                        internalText = clipboardText
                        onTextChanged(clipboardText, clipboardText.length == length)
                    }
                    showPasteButton = false
                }
            )
        }
    }
}

@Composable
private fun CharContainer(
    index: Int,
    text: String,
    otpError: Boolean,
    modifier: Modifier = Modifier
) {
    val isFocused = text.length == index
    val char = if (index < text.length) text[index] else '•'
    val backgroundColor = when {
        isFocused && !otpError -> blueA220
        otpError -> red500
        else -> white100
    }
    val textColor = when {
        index < text.length && !otpError -> gray900
        otpError -> red500
        else -> gray650
    }

    Box(
        modifier = modifier
            .width(40.dp)
            .height(64.dp)
            .background(color = white100, shape = RoundedCornerShape(8.dp))
            .border(
                width = if (isFocused || otpError) 1.dp else 0.dp,
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .onKeyEvent { keyEvent: KeyEvent ->
                when {
                    keyEvent.type == KeyEventType.KeyDown -> return@onKeyEvent true
                    keyEvent.key == Key.Paste -> return@onKeyEvent true
                    else -> false
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = TextValue(text = char.toString()),
            color = textColor,
            fontSize = 40.sp,
        )
    }
}
