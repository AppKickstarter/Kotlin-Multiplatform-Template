package com.lduboscq.appkickstarter.ui

import com.seiko.imageloader.ImageLoaderConfigBuilder
//import com.seiko.imageloader.intercept.BlurInterceptor
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority

fun ImageLoaderConfigBuilder.commonConfig() {
    logger = DebugLogger(LogPriority.VERBOSE)
    interceptor {
       // addInterceptor(BlurInterceptor())
    }
}
