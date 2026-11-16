package kotlin_in_action.section_8_basic_types_collections_and_arrays.basic_types

import kotlin_in_action.Product
import kotlin_in_action.User

// 8.1.7 - The Nothing type: "This function never returns"

/*
    Nothing — тип, який не має жодного значення.
    - Функція з типом Nothing ніколи не завершується звичайним поверненням(н-д, завжди викидає виняток, безкінечний цикл).
    - Nothing є підтипом усіх типів Kotlin, тому його можна використовувати в Elvis та іншими конструкціями.
    - Nothing? може містити лише null.
 */

// Завдання 1 (з класом User)
// Створи функцію з типом Nothing, яка викидає виняток.
// Використай щоб отримати ім'я User або повідомити про помилку.
fun userNotFound(): Nothing{
    throw Exception("User not found")
}

fun getUserName(user: User?): String = user?.name ?: userNotFound()

// Завдання 2
// Завдання 2 (з класом AppEvent)
// Створи функцію з типом Nothing, яка викидає виняток для непідтримуваних даних.
// Використай її у when для обробки невідомих типів подій.
fun unsupportedEvent(data: Any?): Nothing{
    throw IllegalArgumentException("This data type is not supported")
}

fun getEventValue(event: AppEvent): String{
    return when(event.data){
        is String -> event.data
        is Int -> event.data.toString()
        is Boolean -> if(event.data) "Enabled" else "Disabled"
        else -> unsupportedEvent(event)
    }
}

fun main(){
    println("Task 1")
    println(getUserName(User("Anna", 25, "Lutsk")))
//    println(getUserName(null))

    println("\nTask 2")
    println(getEventValue(AppEvent("Message", "Hello")))
    println(getEventValue(AppEvent("Users", 120)))
    println(getEventValue(AppEvent("Dark mode", true)))
    println(getEventValue(AppEvent("Purchase", Product("Laptop", 40000))))
}