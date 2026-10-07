package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.Employee
import kotlin_in_action.employees

// 6.1.10 - Merging collections - zip

/*
    zip — об'єднує два списки в список пар за індексами.
    Можна передати transform-лямбду, щоб одразу перетворити кожну пару.

    Можна використовувати infix-синтаксис, але тоді transform передати не можна.
*/
val bonuses = listOf(500, 300, 800, 400, 600, 1000)

fun main(){
    // Об'єднай працівників зі списком бонусів відповідно
    println("Task 1")
    // можна використовувати infix синтаксис
    val employeeWithBonuses = employees zip bonuses
    employeeWithBonuses.forEach { (employee, bonus) ->
        println("${employee.name} - salary: ${employee.salary}, bonus: $bonus")
    }

    // Створи новий список Employee, в якому зарплатня вже містить бонус
    println("\nTask 2")
    val employeeWithBonuses2 = employees.zip(bonuses) { employee, bonus ->
        Employee(employee.name, employee.age, employee.salary + bonus, employee.department)
    }
    employeeWithBonuses2.forEach { employee->
        println("${employee.name} - salary: ${employee.salary}")
    }

    // Використовуючи employees і bonuses створити список рядків,
    // де для кожного працівника буде показано name, salary + bonuses = new salary
    println("\nTask 3")
    val employeeWithBonuses3 = employees.zip(bonuses) { employee, bonus ->
        "${employee.name}: ${employee.salary} + $bonus = ${employee.salary + bonus}"
    }
    println(employeeWithBonuses3.joinToString(separator = "\n"))
}