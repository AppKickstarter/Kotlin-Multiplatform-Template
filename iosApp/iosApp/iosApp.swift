import UIKit
import shared
import SwiftUI

@available(iOS 16.0, *)
@main
struct iOSApp: App {

    init() {
        SharedModuleKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            MainApp()
        }
    }
}

struct MainApp: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return Main_iosKt.MainController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
