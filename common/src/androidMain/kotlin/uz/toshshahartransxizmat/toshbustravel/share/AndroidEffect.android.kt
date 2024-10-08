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

    // Receiverning ro'yxatdan o'tganligini ko'rsatish flagi
    var isReceiverRegistered by remember { mutableStateOf(false) }

    DisposableEffect(key1 = context) {
        val filter = IntentFilter("smsCodeReceived")
        context.registerReceiver(receiver.value, filter, Context.RECEIVER_NOT_EXPORTED)
        isReceiverRegistered = true

        onDispose {
            // Receiver ro'yxatdan o'tganligini tekshirish
            if (isReceiverRegistered) {
                context.unregisterReceiver(receiver.value)
                isReceiverRegistered = false
            }
        }
    }
}

// SMS kod receiver funksiyasini yaratish
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

// SMS kodni matndan ajratib olish
private fun extractSmsCode(message: String?): String? {
    val regex = "(\\d{4})".toRegex()
    return regex.find(message ?: "")?.value
}