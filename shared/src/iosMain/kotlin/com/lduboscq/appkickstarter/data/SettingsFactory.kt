package com.lduboscq.appkickstarter.data

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual class SettingsFactory {
    actual fun createSettings(): ObservableSettings {
        return NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
    }
}