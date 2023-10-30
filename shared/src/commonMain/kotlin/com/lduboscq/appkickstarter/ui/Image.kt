package com.lduboscq.appkickstarter.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberImagePainter

@Composable
internal fun Image(url: String, modifier: Modifier = Modifier) {
    CompositionLocalProvider(
        LocalImageLoader provides remember { generateImageLoader() },
    ) {
        Image(
            painter = rememberImagePainter(url),
            contentDescription = null,
            modifier = modifier
        )
    }
}
