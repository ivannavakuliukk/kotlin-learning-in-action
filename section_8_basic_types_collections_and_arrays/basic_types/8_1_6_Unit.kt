package kotlin_in_action.section_8_basic_types_collections_and_arrays.basic_types

// 8.1.6 - The unit type - Kotlin`s void

/*
    - Unit — аналог void у Java, але є повноцінним типом із єдиним значенням Unit.
    - Використовується у функціях, які не повертають корисного результату. : Unit можна не вказувати явно.
    - Результат такої функції можна присвоїти змінній типу Unit, на відміну від void у Java.
    - Unit можна використовувати як аргумент типу в generics
      та функціональних типах (() -> Unit).
 */

// Завдання 1
// Створити функцію, яка приймає іншу як аргумент, виконує її і виводить повідомлення
fun executeAction(action: ()-> Unit){
    action()
    println("Action completed")
}

// Завдання 2
// Створити універсальний клас ActionResult, який зберігає Boolean результат виконання та універсальний data
// Написати функцію, яка зберігає AppEvent, виводить повідомлення та повертає ActionResult з data = Unit
data class ActionResult<T>(
    val success: Boolean,
    val data: T
)

fun saveEvent(event: AppEvent): ActionResult<Unit>{
    println("Event ${event.name} saved successfully")
    return ActionResult(success = true, data = Unit)
}

fun main(){
    println("Task 1")
    val executeResult: Unit = executeAction {
        println("User logged in")
    }

    println("\nTask 2")
    val result = saveEvent(AppEvent("Login", "Anna"))
    println(result.success)
    println(result.data)    // kotlin.Unit
}