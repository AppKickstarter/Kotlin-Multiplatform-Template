package com.lduboscq.appkickstarter

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.lduboscq.appkickstarter.list.PersonsListScreen

@Composable
fun MainApp() {
    Navigator(PersonsListScreen()) { navigator ->
        SlideTransition(navigator)
    }
}
