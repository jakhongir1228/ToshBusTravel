import SwiftUI
import common
import GoogleMaps

@main
struct iOSApp: App {

    init() {
        InitKoinKt.doInitKoin(appDeclaration: {_ in })
        GMSServices.provideAPIKey("AIzaSyCKZtX5J6h3LWwl3e86P77ce0vuHaiZOv0")
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




