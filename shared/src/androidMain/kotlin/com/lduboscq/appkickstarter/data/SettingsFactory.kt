package com.lduboscq.appkickstarter.data

import android.content.Context
import android.preference.PreferenceManager
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory(private val context: Context) {
    actual fun createSettings(): ObservableSettings {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        return SharedPreferencesSettings(sharedPrefs)
    }
}