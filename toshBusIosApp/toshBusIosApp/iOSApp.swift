import SwiftUI
import common
import GoogleMaps

@main
struct iOSApp: App {
    
    init() {
        InitKoinKt.doInitKoin(appDeclaration: {_ in })
         GMSServices.provideAPIKey("AIzaSyCx3s1gHi5ldALIGVEXNk3Lb7Fym0DwATI")
         initializeIOSDeviceIdProvider()

    }

    var body: some Scene {
            WindowGroup {
                ZStack {
                ComposeView(locationProvider: LocationProvider())
                }
            }
        }
}

/*
struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        return MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        print("update")
    }
}*/
