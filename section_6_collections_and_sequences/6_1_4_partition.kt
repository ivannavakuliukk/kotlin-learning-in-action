package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.Employee
import kotlin_in_action.employees

// 6.1.4 - splitting a list into a pair of lists

/*
    partition - розділяє колекцію на дві групи за умовою.
    Повертає Pair з двох списків:
    перший → елементи, для яких умова true
    другий → елементи, для яких умова false.

    Додатково: joinToString - об'єднує елементи колекції в один String.
    За замовчуванням розділяє елементи комою та пробілом.

 */

fun main() {
    // Розділи працівників на дві групи: тих, у кого зарплата 50000
    // і більше, та тих, у кого зарплата менша за 50000. Виведи обидві групи.
    val (lowSalary, highSalary) = employees.partition { it.salary < 50000 }
    println("Low salary: $lowSalary, \nHigh salary: $highSalary")

    // Розділи працівників на дві групи: працівників з відділу IT, які старші 25 років,
    // та всіх інших. Порахуй кількість працівників у кожній групі.
    val(oldItEmployees, others) = employees.partition { it.department == "IT" && it.age > 25}
    val oldItEmployeesCount = oldItEmployees.size
    val othersCount = others.size
    println(
        "it employees older than 25: $oldItEmployeesCount - $oldItEmployees\n" +
                "others: $othersCount - $others"
    )

    // Розділи працівників на дві групи: тих, хто має зарплату більше 50000 або працює в IT
    // та всіх інших. Виведи імена працівників з обох груп.
    val predicate = {employee: Employee -> employee.department == "IT" || employee.salary > 50000}
    val (comeIn, stayOut) = employees.partition(predicate)
    println("Has salary more than 50000 or works in IT: ${comeIn.joinToString { it.name}}")
    println("Others: ${stayOut.joinToString { it.name }}")
}