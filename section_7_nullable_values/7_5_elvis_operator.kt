package kotlin_in_action.section_7_nullable_values

import kotlin_in_action.User
import kotlin_in_action.users

// 7.5 - Providing default values in null cases with elvis operator

/*
    Elvis operator (?:) — дозволяє задати значення, яке буде використане,
    якщо вираз зліва дорівнює null.
*/

// Написати функцію, яка повертає вік користувача,
// якщо null - повертати 18 за замовчуванням

fun getUserAge(user: User): Int = user.age ?: 18

// Написати функцію, яка повертає місто користувача,
// якщо місто null - кидаємо IllegalArgumentException

fun validateUserCity(user: User): String {
    return user.city ?: throw IllegalArgumentException("User ${user.name} doesn't have a city")
}

fun main(){
    println("Task 1")
    println(users.joinToString(separator = "\n") {
        it.name + " - " + getUserAge(it)
    })
    println("\nTask 2")
    val user1 = User("Bob", 12, null)
    val user2 = User("Emily", null, "Monaco")
    println(validateUserCity(user2))
    println(validateUserCity(user1))
}

