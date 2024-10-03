package uz.toshshahartransxizmat.toshbustravel.share

import androidx.compose.runtime.Composable
import platform.Foundation.NSNotification
import platform.Foundation.NSNotificationCenter
import platform.darwin.NSObject
import platform.UIKit.UIApplicationDidEnterBackgroundNotification
import platform.UIKit.UIApplicationWillEnterForegroundNotification
import platform.UIKit.UIApplicationWillTerminateNotification
import platform.UIKit.UIKeyboardWillHideNotification
import platform.UIKit.UIKeyboardWillShowNotification
import platform.UIKit.UIResponder

private var keyboardVisible = false

private class KeyboardObserver : NSObject() {
    fun handleKeyboardWillShow(notification: NSNotification) {
        keyboardVisible = true
    }

    fun handleKeyboardWillHide(notification: NSNotification) {
        keyboardVisible = false
    }
}

fun setupKeyboardObservers() {
    val defaultCenter = NSNotificationCenter.defaultCenter()
    val observer = KeyboardObserver()

    defaultCenter.addObserver(
        observer,
        selector = platform.objc.sel_registerName("handleKeyboardWillShow:"),
        name = UIKeyboardWillShowNotification,
        `object` = null
    )

    defaultCenter.addObserver(
        observer,
        selector = platform.objc.sel_registerName("handleKeyboardWillHide:"),
        name = UIKeyboardWillHideNotification,
        `object` = null
    )
}

@Composable
actual fun isImeVisible(): Boolean = keyboardVisible