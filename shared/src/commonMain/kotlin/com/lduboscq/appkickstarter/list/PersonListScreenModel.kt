package com.lduboscq.appkickstarter.list

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.lduboscq.appkickstarter.data.PersonRepository
import com.lduboscq.appkickstarter.model.Person
import kotlinx.coroutines.launch

class PersonListScreenModel(
    private val personRepository: PersonRepository
) : StateScreenModel<PersonListScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        object Error : State()
        data class Result(val persons: List<Person>) : State()
    }

    init {
        getPersons()
    }

    private fun getPersons() {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result(persons = personRepository.getPosts())
        }
    }
}