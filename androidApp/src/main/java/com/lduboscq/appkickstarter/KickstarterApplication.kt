package com.lduboscq.appkickstarter

import android.app.Application
import com.lduboscq.appkickstarter.di.androidAppModule
import com.lduboscq.appkickstarter.di.initKoin
import com.lduboscq.appkickstarter.ui.appContextForImagesMP
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class KickstarterApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())

        appContextForImagesMP = this

        initKoin {
            modules(
                listOf(
                    module { androidContext(this@KickstarterApplication) },
                    androidAppModule
                )
            )
        }
    }
}
