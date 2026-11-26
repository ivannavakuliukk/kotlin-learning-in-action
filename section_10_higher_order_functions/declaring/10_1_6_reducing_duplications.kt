package kotlin_in_action.section_10_higher_order_functions.declaring

import kotlin_in_action.Employee
import kotlin_in_action.employees

// 10.1.6 - Making code more reusable by reducing duplications with lambdas

/*
    Функції вищого порядку дозволяють усувати дублювання коду, відокремлюючи спільну логіку алгоритму від змінної поведінки,
    яка передається через функціональні параметри.

    Strategy — поведінковий патерн проєктування (Behavioral Design Pattern), який дозволяє визначити сімейство алгоритмів,
    інкапсулювати кожен із них, зробити їх взаємозамінними та змінювати поведінку програми під час виконання. Патерн складається з:
    - Strategy — спільний інтерфейс, який визначає контракт для всіх алгоритмів.
    - Concrete Strategy — конкретна реалізація алгоритму.
    - Context — об'єкт, який зберігає посилання на стратегію та делегує їй виконання алгоритму.

    У Kotlin класичний Strategy можна спростити за допомогою функцій вищого порядку: інтерфейс замінити функціональним типом,
    конкретні класи стратегій — лямбдами, а контекст — універсальною функцією.
    Класична реалізація залишається доцільною, коли стратегії мають складну логіку, власний стан, залежності або декілька пов'язаних методів.
*/

// Завдання 1: Реалізувати класичний патерн Strategy для формування різних звітів про працівників.
// Забезпечити можливість зміни стратегії під час виконання програми.
interface EmployeeReportStrategy {
    fun generateReport(employees: List<Employee>): String
}

class HighSalaryReport(private val minSalary: Int): EmployeeReportStrategy {
    override fun generateReport(employees: List<Employee>): String {
        val matchingEmployees = employees.filter{it.salary > minSalary}
        return "High Salary report(more than $minSalary)" +
                "\nEmployees: ${matchingEmployees.joinToString { it.name }}" +
                "\nAmount: ${matchingEmployees.count()}" +
                "\nTotal salary: ${matchingEmployees.sumOf { it.salary }}"

    }
}

class DepartmentReport(private val department: String): EmployeeReportStrategy {
    override fun generateReport(employees: List<Employee>): String {
        val matchingEmployees = employees.filter{it.department == department}
        return "Department report \"$department\"" +
                "\nEmployees: ${matchingEmployees.joinToString { it.name }}" +
                "\nAmount: ${matchingEmployees.count()}" +
                "\nTotal salary: ${matchingEmployees.sumOf { it.salary }}"
    }
}

class AgeReport(private val minAge: Int, private val maxAge: Int): EmployeeReportStrategy {
    override fun generateReport(employees: List<Employee>): String {
        val matchingEmployees = employees.filter{it.age in minAge .. maxAge}
        return "Age report(employees $minAge-$maxAge) " +
                "\nEmployees: ${matchingEmployees.joinToString { it.name }}" +
                "\nAmount: ${matchingEmployees.count()}" +
                "\nTotal salary: ${matchingEmployees.sumOf { it.salary }}"
    }

}

class EmployeeReportGenerator(private var strategy: EmployeeReportStrategy){
    fun generateReport(employees: List<Employee>): String {
        return strategy.generateReport(employees)
    }

    fun setStrategy(newStrategy: EmployeeReportStrategy){
       strategy = newStrategy
    }
}

// Завдання 2: Усунути дублювання коду формування звітів за допомогою функції вищого порядку,
// передаючи критерії відбору працівників через лямбди.
fun generateEmployeeReport(
    employees: List<Employee>,
    title: String,
    predicate: (Employee) -> Boolean
): String{
    val matchingEmployees = employees.filter(predicate)
    return "$title " +
            "\nEmployees: ${matchingEmployees.joinToString { it.name }}" +
            "\nAmount: ${matchingEmployees.count()}" +
            "\nTotal salary: ${matchingEmployees.sumOf { it.salary }}"
}

fun main() {
    println("Task 1")
    val generator = EmployeeReportGenerator(HighSalaryReport(40000))
    println(generator.generateReport(employees))

    generator.setStrategy(DepartmentReport("IT"))
    println()
    println(generator.generateReport(employees))

    generator.setStrategy(AgeReport(25, 40))
    println()
    println(generator.generateReport(employees))

    println("\nTask 2")
    val highSalaryReport = generateEmployeeReport(
        employees,
        "High Salary Report(more than 40000)",
        { it.salary > 40000 }
    )
    println()
    println(highSalaryReport)

    val departmentReport = generateEmployeeReport(
        employees = employees,
        title = "Department report \"IT\"",
        predicate = {it.department == "IT" }
    )
    println()
    println(departmentReport)

    val ageReport = generateEmployeeReport(
        employees = employees,
        title = "Age report 25-40",
        predicate = {it.age in 25 .. 40 }
    )
    println()
    println(ageReport)
}