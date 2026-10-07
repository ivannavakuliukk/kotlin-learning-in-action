package kotlin_in_action.section_7_nullable_values

import kotlin_in_action.users

// 7.4 - Combining null checks and method calls with the safe-call operator

/*
    Safe-call operator (?.) — дозволяє безпечно звертатися до властивостей
    або методів nullable-значення.

    Якщо значення null, операція не виконується і повертається null.
*/

fun main(){
    // Для кожного користувача виведи довжину назви міста
    println("Task 1")
    println(
        users.joinToString(separator = "\n") {
            it.name + " - " + it.city?.length
        }
    )

    // Для кожного користувача отримай першу літеру міста у нижньому регістрі
    println("\nTask 2")
    println(
        users.joinToString(separator = "\n") {
            it.name + " - " + it.city?.first()?.lowercase()
        }
    )

}