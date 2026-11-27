package kotlin_in_action.section_10_higher_order_functions.inline_functions

import kotlin_in_action.Employee
import kotlin_in_action.employees

// 10.2.1 - Inlining means substituting the function body to each call site

/*
    Inlining (вбудовування) — механізм оптимізації, за якого компілятор підставляє тіло функції безпосередньо
    в місце її виклику (call site) на етапі компіляції замість генерації звичайного виклику функції в runtime.
    Для цього використовується модифікатор inline.
    Використовується саме для оптимізації функцій вищого порядку:
    - Звичайна функція вищого порядку — лямбда може бути представлена окремим об'єктом,
     а її виконання здійснюється через метод invoke(). Це створює overhead.
    - Inline-функція з безпосередньо переданою лямбдою — підставляється як тіло функції, так і тіло лямбди.
    - Inline-функція зі змінною функціонального типу — підставляється тіло функції, але лямбда не вбудовується.

    * Використання inline корисне саме для higher-order functions, бо для звичайних JVM і так має підтримку вбудувань.
    * Якщо функція має великий розмір - її краще не вбудовувати, бо це може значно збільшити об'єм байт-коду.
    * Всі ідіоматичні функції над колекціями (filter, map...) є inline в Kotlin.

*/

// Перевірити байт-код для 3-ьох випадків:
// У верхньому меню вибрати Tools → Kotlin → Show Kotlin Bytecode → Decompile.
// У декомпільованому main знайти три варіанти


// Завдання 1: Реалізувати звичайну функцію вищого порядку для вимірювання часу виконання переданої
// операції та повернення її результату.
fun <T> measureExecution(
    operationName: String,
    action: () -> T
): T{
    val startTime = System.nanoTime()
    val result = action()
    val actionTime = System.nanoTime() - startTime
    println("$operationName cost time: $actionTime ns")
    return result
}

// Завдання 2: Реалізувати аналогічну inline-функцію. Перевірити передавання лямбди безпосередньо
// та через змінну функціонального типу.
inline fun <T> measureExecutionInline(
    operationName: String,
    action: () -> T
): T {
    val startTime = System.nanoTime()
    val result = action()
    val actionTime = System.nanoTime() - startTime
    println("$operationName cost time: $actionTime ns")
    return result
}

fun main() {
    println("Task 1")
    println("Regular higher-order function")
    val operation: () -> List<Employee> = {
        employees
            .filter { it.salary > 40000 }
            .sortedByDescending { it.salary }
    }
    measureExecution("Find high paid employees",operation)


    println("\nTask 2")
    println("Inline higher-order function with functional type value passed")
    measureExecutionInline(
        "Find high paid employees",
        operation
    )

    println("Inline higher-order function with lambda passed")
    measureExecutionInline(
        "Find high paid employees",
        {
            employees
                .filter { it.salary > 40000 }
                .sortedByDescending { it.salary }
        }
    )
}