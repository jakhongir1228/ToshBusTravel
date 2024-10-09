package uz.toshshahartransxizmat.toshbustravel.share

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.ModalBottomSheet

@RequiresApi(Build.VERSION_CODES.O)
@Composable
actual fun AndroidEffect(onSmsReceived: (String) -> Unit) {
    val context = LocalContext.current
    val receiver = rememberUpdatedState(smsCodeBroadcastReceiver(onSmsReceived))

    var isReceiverRegistered by remember { mutableStateOf(false) }

    DisposableEffect(key1 = context) {
        val filter = IntentFilter("smsCodeReceived")
        context.registerReceiver(receiver.value, filter, Context.RECEIVER_NOT_EXPORTED)
        isReceiverRegistered = true

        onDispose {
            if (isReceiverRegistered) {
                context.unregisterReceiver(receiver.value)
                isReceiverRegistered = false
            }
        }
    }
}

private fun smsCodeBroadcastReceiver(onSmsReceived: (String) -> Unit): BroadcastReceiver {
    return object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "smsCodeReceived") {
                val message = intent.getStringExtra("message")
                val code = extractSmsCode(message)
                if (code != null) {
                    onSmsReceived(code)
                }
            }
        }
    }
}

private fun extractSmsCode(message: String?): String? {
    val regex = "(\\d{4})".toRegex()
    return regex.find(message ?: "")?.value
}