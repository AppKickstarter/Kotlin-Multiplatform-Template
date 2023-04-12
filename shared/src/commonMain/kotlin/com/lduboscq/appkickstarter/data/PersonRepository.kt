package com.lduboscq.appkickstarter.data

import com.lduboscq.appkickstarter.model.Person

class PersonRepository {
    private val persons = mutableListOf<Person>()

    init {
        persons.addAll(
            listOf(
                Person("1", "Mariah", "android dev"),
                Person("20", "Lucy", "iOS dev"),
            )
        )
    }

    fun getPost(id: String): Person? {
        return persons.firstOrNull { it.id == id }
    }

    fun getPosts(): List<Person> {
        return persons
    }
}
