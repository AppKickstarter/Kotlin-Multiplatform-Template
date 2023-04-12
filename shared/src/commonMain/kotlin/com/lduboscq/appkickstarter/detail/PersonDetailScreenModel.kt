package com.lduboscq.appkickstarter.detail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.lduboscq.appkickstarter.data.PersonRepository
import com.lduboscq.appkickstarter.model.Person
import kotlinx.coroutines.launch

class PersonDetailScreenModel(
    personId: String,
    private val repository: PersonRepository
) : StateScreenModel<PersonDetailScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        object Error : State()
        data class Result(val person: Person) : State()
    }

    init {
        getPerson(personId)
    }

    private fun getPerson(id: String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = repository.getPost(id)?.let { State.Result(person = it) } ?: State.Error
        }
    }
}
