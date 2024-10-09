import SwiftUI
import common
import GoogleMaps
import GooglePlaces

@main
struct iOSApp: App {

    init() {
        InitKoinKt.doInitKoin(appDeclaration: {_ in })
        GMSServices.provideAPIKey("AIzaSyCKZtX5J6h3LWwl3e86P77ce0vuHaiZOv0")
        GMSPlacesClient.provideAPIKey("AIzaSyD7i6d8teRDVvWJ3SdeATBrG74liSChL5I")
        // Klaviatura kuzatuvchilarini o'rnatish
        PlatformModuleKt.setupKeyboardObservers()
    }

    var body: some Scene {
            WindowGroup {
                ZStack {
                    ComposeView()
                }
            }
        }
}
struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        return MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        print("update")
    }
}




