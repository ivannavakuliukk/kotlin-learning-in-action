package kotlin_in_action.section_8_basic_types_collections_and_arrays.basic_types

import kotlin_in_action.Product
import kotlin_in_action.User

// 8.1.5 - Any, Any?: the root of Kotlin type hierarchy

/*
    Any - клас від якого наслідуються всі типи Kotlin.
    Від нього всі класи наслідують toString(), hashCode(), equals().
    Any компілюється до Object в Java байткоді.
    Для того, щоб зберігати null - використовувати Any?

 */

// Завдання
// Написати клас AppEvent, який зберігає назву події та дані будь-якого типу
// Також функцію, яка логує подію. Використати класи User і Product
data class AppEvent(
    val name: String,
    val data: Any?
)

fun logEvent(event: AppEvent){
    val eventDescription = when(event.data){
        is User -> "${event.name}: User ${event.data.name}, age ${event.data.age}"
        is Product -> "${event.name}: Product ${event.data.name} cost ${event.data.price}"
        null -> "${event.name}: No data"
        else -> "${event.name}: ${event.data}"
    }
    println(eventDescription)
}

fun main(){
    val events = listOf(
        AppEvent("Login", User(name = "Anna", age = 25, city = null)),
        AppEvent("Purchase", Product("Laptop", 35000)),
        AppEvent("Online users", 120),
        AppEvent("Dark mode", true),
        AppEvent("Error", null),
        AppEvent("Message", "Application started")
    )
    events.forEach {
        logEvent(it)
    }
}