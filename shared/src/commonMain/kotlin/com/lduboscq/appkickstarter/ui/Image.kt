package com.lduboscq.appkickstarter.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun Image(url: String, modifier: Modifier = Modifier) {
    CompositionLocalProvider(
        LocalImageLoader provides generateImageLoader(),
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = null,
            modifier = modifier
        )
    }
}