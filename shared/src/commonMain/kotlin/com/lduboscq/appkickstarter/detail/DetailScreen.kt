package com.lduboscq.appkickstarter.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.di.getScreenModel
import org.koin.core.parameter.parametersOf

internal data class PersonDetailScreen(val personId: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel: PersonDetailScreenModel = getScreenModel { parametersOf(personId) }
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        if (state is PersonDetailScreenModel.State.Result) {
            PersonDetailScreenContent((state as PersonDetailScreenModel.State.Result).person) {
                navigator.pop()
            }
        }
    }
}
