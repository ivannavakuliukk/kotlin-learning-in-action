package kotlin_in_action.section_10_higher_order_functions.declaring

import kotlin_in_action.Employee
import kotlin_in_action.employees

// 10.1.4 - Parameters with function types can provide defaults or be null

/*
    Функціональні параметри, як і звичайні параметри функцій, можуть мати значення за замовчуванням або бути nullable.
    Для безпечного виклику nullable-функції використовується ?.invoke()
*/

// Завдання 1: Реалізувати форматування інформації про працівників
// із функціональним параметром зі значенням за замовчуванням.
fun formatEmployees(
    employees: List<Employee>,
    transform: (employee: Employee)-> String = { employee-> employee.name }
): String {
    val result = StringBuilder()
    for (employee in employees){
        result.append(transform(employee))
        result.append('\n')
    }
    return result.toString()
}

// Завдання 2: Реалізувати розрахунок загальної зарплати
// з необов'язковим nullable-функціональним параметром для коригування зарплати.
fun calculateTotalSalary(
    employees: List<Employee>,
    adjustSalary: ((Int) -> Int)? = null
): Int {
    return employees.sumOf { employee ->
        adjustSalary?.invoke(employee.salary) ?: employee.salary
    }
}

fun main() {
    println("Task 1")
    println("Employees names:\n" +
            formatEmployees(employees)
    )
    println("Employees salaries:\n" +
            formatEmployees(employees){employee-> "${employee.name} - ${employee.salary}$"}
    )
    println("Employees departments:\n" +
        formatEmployees(employees){employee-> "${employee.name} - ${employee.department}$"}
    )


    println("\nTask 2")
    println("Total salary: ${calculateTotalSalary(employees)}$")
    println("Total salary plus bonus: ${calculateTotalSalary(employees){salary -> salary + salary * 10/100}} $")
}