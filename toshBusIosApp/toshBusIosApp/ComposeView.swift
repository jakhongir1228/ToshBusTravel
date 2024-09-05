import SwiftUI
import common // Your KMM shared module

struct ComposeView: UIViewControllerRepresentable {
    let locationProvider: LocationProvider

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewController(locationProvider: locationProvider)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // No-op
    }
}
