package uz.toshshahartransxizmat.toshbustravel.share

import platform.UIKit.UIDevice

class IOSDeviceIdProvider : DeviceIdProvider {
   override fun getDeviceId(): String {
      return UIDevice.currentDevice.identifierForVendor?.UUIDString ?: ""
   }
}

fun initializeIOSDeviceIdProvider() {
   deviceIdProvider = IOSDeviceIdProvider()
}