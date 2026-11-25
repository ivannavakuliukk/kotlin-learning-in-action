package kotlin_in_action.section_9_operators_and_conventions.destructing_declarations

import kotlin_in_action.employees

// 9.4.2 - Ignoring destructed values using the _ character

/*
    Символ _ дозволяє пропускати непотрібні компоненти під час деструктуризації.
    Для пропущеного компонента не створюється локальна змінна та не викликається відповідна функція componentN().
*/


fun main() {
    // Завдання 1: Використати _ для пропуску непотрібних
    // компонентів Employee та Triple під час деструктуризації.
    println("Task 1")
    for((name, _, salary) in employees) {
        println("$name has $salary$")
    }
    val (_, _, avg) = calculateSalaryStatistics(employees)
    println("Avg salary is $avg")
}