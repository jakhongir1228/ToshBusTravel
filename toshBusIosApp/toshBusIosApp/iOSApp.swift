import SwiftUI
import common

@main
struct iOSApp: App {
    var body: some Scene {
            WindowGroup {
                ZStack {
                    Color(uiColor: UIColor(red: 0.0, green: 0.3137255, blue: 0.39607844, alpha: 1.0))
                     .ignoresSafeArea(.all)
                    ComposeView()
                }.preferredColorScheme(.dark)
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
