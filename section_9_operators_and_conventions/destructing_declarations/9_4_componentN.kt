package kotlin_in_action.section_9_operators_and_conventions.destructing_declarations

import kotlin_in_action.Employee
import kotlin_in_action.employees
import kotlin_in_action.section_9_operators_and_conventions.Point
import kotlin_in_action.section_9_operators_and_conventions.Rectangle
import kotlin.math.absoluteValue

// 9.4 - brief destructing declaration overview

/*
    Destructuring declaration (деструктуризація) — механізм, який дозволяє розкласти об'єкт на декілька змінних в одному оголошенні.
    - Вираз val (a, b) = obj перетворюється на виклики obj.component1() та obj.component2().
    - Для реалізації деструктуризації використовуються операторні функції componentN().
    - Для data class компілятор автоматично генерує componentN() для властивостей первинного конструктора в порядку їх оголошення.
    - Для списків і масивів стандартна бібліотека надає функції від component1() до component5().
    - Pair<A, B> та Triple<A, B, C> — стандартні класи для зберігання двох або трьох значень, зокрема різних типів.
     Зручно використовувати для повернення декількох значень із функції. Pair можна створити через конструктор або інфіксну функцію to,
     а Triple — через конструктор.
*/

// Завдання 1: Розширити деструктуризацію data class Rectangle
// власними component3() і component4() для ширини та висоти.
operator fun Rectangle.component3(): Int = (upperLeft.x - lowerRight.x).absoluteValue
operator fun Rectangle.component4(): Int = (upperLeft.y - lowerRight.y).absoluteValue

// Завдання 2: Реалізувати componentN() у звичайному класі
// FlightTicket для деструктуризації властивостей конструктора.
class FlightTicket(
    val flightNumber: String,
    val destination: String,
    val seat: String
){
    operator fun component1() = flightNumber
    operator fun component2() = destination
    operator fun component3() = seat
}

// Завдання 4: Використати Pair і Triple для повернення
// статистики зарплат та отримання її через деструктуризацію
fun findSalaryBounds(employees: List<Employee>): Pair<Int, Int>{
    val min = employees.minOf { it.salary }
    val max = employees.maxOf { it.salary }
    return min to max
}

fun calculateSalaryStatistics(employees: List<Employee>): Triple<Int, Int, Double>{
    val (min, max) = findSalaryBounds(employees)
    val average = employees.map(Employee::salary).average()
    return Triple(min, max, average)
}


fun main() {
    println("Task 1")
    val rectangle = Rectangle(Point(0,0), Point(2,5))
    val (upperLeft, lowerRight, width, height) = rectangle
    println("UpperLeft: $upperLeft, lowerRight: $lowerRight, height: $height, width: $width")

    println("\nTask 2")
    val ticket = FlightTicket("234", "London", "2C")
    val (flightNumber, destination, seat) = ticket
    println("Destination: $destination, seat: $seat, flightNumber: $flightNumber")

    // Завдання 3: Використати стандартні componentN() списку
    // для отримання трьох призерів змагання.
    println("\nTask 3")
    val participants = listOf("Anna", "Max", "Alex", "Kate", "Bob")
    val (gold, silver, bronze) = participants
    println("Contest result: gold=$gold, silver=$silver, bronze=$bronze")

    println("\nTask 4")
    val employees = employees
    val (minSalary, maxSalary, averageSalary) = calculateSalaryStatistics(employees)
    println("Min: $minSalary, max: $maxSalary, average: $averageSalary")
}