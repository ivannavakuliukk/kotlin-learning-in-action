package kotlin_in_action.section_10_higher_order_functions.inline_functions

import kotlin_in_action.employees

// 10.2.2 - Restrictions on inline unctions

/*
    inline-параметр (лямбду) можна викликати безпосередньо або передавати як аргумент іншій inline функції.
    Однак його не можна:
    - Зберігати у змінних, властивостях або колекціях.
    - Повертати як результат функції.
    - Передавати у звичайні (non-inline) функції.
    - Захоплювати в лямбдах, які виконуватимуться поза поточним контекстом виклику.
    Модифікатор noinline дозволяє вибірково вимкнути вбудовування окремих функціональних параметрів.
*/

// Завдання 1: Реалізувати реєстр операцій для збереження та відкладеного виконання функціональних значень.
// Створити inline-функцію, яка виконує передану операцію, повертає її результат та реєструє сповіщення.
// Використати noinline для параметра, який необхідно зберегти для подальшого виконання.
class OperationRegistry{
    private val operations = mutableMapOf<String, () -> Unit>()

    fun register(name: String, operation: () -> Unit) {
        operations[name] = operation
    }

    fun execute(name: String){
        operations[name]?.invoke()
    }
}

inline fun <T> executeWithNotification(
    registry: OperationRegistry,
    name: String,
    action: () -> T,
    noinline notification: (T) -> Unit
): T{
    val result = action()
    println(result)
    registry.register(name) { notification(result) }
    return result
}


fun main() {
    println("Task 1")
    val registry = OperationRegistry()
    executeWithNotification(
        registry,
        "Notification",
        action = {
            employees
                .filter { it.salary > 40000 }
                .sortedByDescending { it.salary }
        }
    ){
        println("Found ${it.size} employees with high salary")
    }
    registry.execute( "Notification")
}