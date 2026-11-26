package kotlin_in_action.section_10_higher_order_functions.declaring

import kotlin_in_action.Employee
import kotlin_in_action.employees

// 10.1.1 - Function types specify the parameter types and return values of a lambda

/*
    Function type (функціональний тип) — тип, який визначає типи параметрів та тип значення, що повертається функцією або лямбда-виразом.
    Загальний синтаксис: (ParameterTypes) -> ReturnType.
    - Функціональний тип можна вказувати явно або використовувати автоматичне визначення типу (type inference).
    - Імена параметрів у функціональному типі необов'язкові та покращують читабельність.
    - Unit використовується, якщо функція не повертає значущого результату, треба вказувати явно.
    - (String) -> Int? — функція, яка може повертати null.
    - ((String) -> Int)? — nullable-функціональний тип, для безпечного виклику якого використовується ?.invoke().
*/

fun main() {
    // Завдання 1: Оголосити функціональні типи для математичних
    // операцій, використовуючи явне та автоматичне визначення типів.
    println("Task 1")
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    val multiply = { x: Int, y: Int -> x * y }
    val subtract: (Int, Int) -> Int = { x, y -> x - y }

    println("Sum: ${sum(10, 2)}")
    println("Multiply: ${multiply(10, 2)}")
    println("Subtract: ${subtract(10, 2)}")


    // Завдання 2: Реалізувати функціональні типи для роботи
    // з Employee з різними типами повернення: Boolean, String та Unit.
    println("\nTask 2")
    val salaryMoreThan2000: (employee:Employee) -> Boolean = { employee -> employee.salary > 2000 }
    val employeeDescription: (Employee) -> String = {
        employee -> "Employee ${employee.name} works in ${employee.department}"
    }
    val printEmployee: (Employee) -> Unit = {employee ->
        println("Employee ${employee.name}, department: ${employee.department}, salary: ${employee.salary}, age: ${employee.age}")
    }

    println("Has salary more than 2000: ${salaryMoreThan2000(employees[0])}")
    println(employeeDescription(employees[0]))
    printEmployee(employees[0])
    println(employees.filter(salaryMoreThan2000))


    // Завдання 3: Дослідити різницю між nullable-функціональним
    // типом та функцією з nullable-типом повернення,
    // використовуючи безпечний виклик через ?.invoke().
    println("\nTask 3")
    val stringToInt: (String) -> Int? = { string -> string.toIntOrNull() }
    var stringLength: ((String) -> Int)? = { string -> string.length }

    println(stringToInt("1"))
    println(stringToInt("One"))

    println(stringLength?.invoke("Hello"))
    stringLength = null
    println(stringLength?.invoke("Hello"))
}