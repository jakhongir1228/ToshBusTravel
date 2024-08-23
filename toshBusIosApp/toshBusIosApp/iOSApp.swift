import SwiftUI
import common
import GoogleMaps

@main
struct iOSApp: App {
    
    init() {
        InitKoinKt.doInitKoin(appDeclaration: {_ in })
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
