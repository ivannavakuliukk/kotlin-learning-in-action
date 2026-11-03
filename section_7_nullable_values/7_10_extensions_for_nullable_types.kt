package kotlin_in_action.section_7_nullable_values

import kotlin_in_action.User
import kotlin_in_action.users

// 7.10 - Extending types without the safe-call operator. Extensions for nullable types

/*
    Extensions for nullable types — функції розширення, які дозволяють викликати функцію навіть для null-значень
    без використання safe-call оператора ?.. Для цього receiver оголошується nullable (наприклад, String?),
    а обробка null виконується безпосередньо всередині функції.
 */

// Завдання 1 (з класом User)
// Реалізувати функцію розширення для класу User, яка дозволяє отримати коротку інфо про користувача.
// Опрацювати випадки коли вік відсутній та користувач відсутній.
// Не використовувати safe-call при виклику функції
fun User?.getShortInfo(): String{
    return when{
        this == null -> "User not found"
        age == null -> "$name, age not found"
        else -> "$name, $age years old"
    }
}

fun main(){
    println("Task 1")
    val usersList = users + null
    usersList.forEach { println(it.getShortInfo()) }
}