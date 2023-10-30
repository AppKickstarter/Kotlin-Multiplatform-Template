package com.lduboscq.appkickstarter.ui

import android.content.Context
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.defaultImageResultMemoryCache
import com.seiko.imageloader.option.androidContext
import okio.Path.Companion.toOkioPath

lateinit var appContextForImagesMP: Context

actual fun generateImageLoader(): ImageLoader {
    return ImageLoader {
        options {
            androidContext(appContextForImagesMP)
        }
        components {
            setupDefaultComponents()
        }
        interceptor {
            // cache 100 success image result, without bitmap
            defaultImageResultMemoryCache()
            memoryCacheConfig {
                // Set the max size to 25% of the app's available memory.
                maxSizePercent(appContextForImagesMP, 0.25)
            }
            diskCacheConfig {
                directory(appContextForImagesMP.cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}
