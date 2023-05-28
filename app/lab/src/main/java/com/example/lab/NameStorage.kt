package com.example.lab

object NameStorage {
    private val firstNames = listOf(
        "My name is Jeff",
        "Liam",
        "Noah",
        "Oliver",
        "Elijah",
        "William",
        "James",
        "Benjamin",
        "Lucas",
        "Henry",
        "Alexander",
        "Olivia",
        "Emma",
        "Ava",
        "Charlotte",
        "Sophia",
        "Amelia",
        "Isabella",
        "Mia",
        "Evelyn",
        "Harper"
    )
    private val secondNames = listOf(
        "My name is Jeff",
        "Liam",
        "Noah",
        "Oliver",
        "Elijah",
        "William",
        "James",
        "Benjamin",
        "Lucas",
        "Henry",
        "Alexander",
        "Olivia",
        "Emma",
        "Ava",
        "Charlotte",
        "Sophia",
        "Amelia",
        "Isabella",
        "Mia",
        "Evelyn",
        "Harper"
    )
    fun random() = firstNames.random() + " " + secondNames.random()
}