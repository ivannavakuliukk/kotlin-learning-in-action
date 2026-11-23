package kotlin_in_action.section_9_operators_and_conventions.overloading_comparisons_operators

import kotlin_in_action.section_9_operators_and_conventions.Money

// 9.2.2 - Ordering operators: compareTo(<, >, ?, and >=)

/*
    Ordering operators (оператори порівняння порядку) — дозволяють порівнювати об'єкти через compareTo().
    - compareTo() повертає Int: від'ємне число — менше, 0 — рівне за порядком, додатне — більше.
    - Перевантажується через operator fun compareTo() як метод класу або extension-функція.
    - Comparable<T> — інтерфейс для визначення природного порядку об'єктів через compareTo().
    Реалізується в класі та дозволяє використовувати sorted().
    - Comparator<T> — інтерфейс для альтернативного порівняння через compare().
     Дозволяє сортувати об'єкти за різними критеріями через sortedWith(), не змінюючи клас.
    - compareValuesBy() — порівняння за кількома властивостями.
*/

// Завдання 1: Перевантажити compareTo() для Money через extension-функцію
// та перевірити оператори <, >, <=, >=.
operator fun Money.compareTo(other: Money): Int {
    return amount.compareTo(other.amount)
}

// Завдання 2: Реалізувати Comparable<Student> для природного сортування
// за оцінкою та іменем через compareValuesBy().
data class Student(val name: String, val grade: Int): Comparable<Student> {
    override fun compareTo(other: Student): Int {
        return compareValuesBy(this, other, Student::grade, Student::name)
    }
}

fun main() {
    println("Task 1")
    val amount1 = Money(20)
    val amount2 = Money(20)
    val amount3 = Money(30)

    println(amount1 > amount2)
    println(amount2 >= amount1)
    println(amount3 < amount2)
    println(amount1 <= amount3)


    println("\nTask 2")
    val anna = Student("Anna", 90)
    val bob = Student("Bob", 75)
    val alex = Student("Alex", 90)
    val students = listOf(anna, bob, alex)

    println(anna > alex)
    println(alex < anna)
    println(students.sorted().map { it.name })

    // Завдання 3: Створити альтернативні компаратори через Comparator
    println("\nTask 3")
    val byName: Comparator<Student> = compareBy { it.name }
    val byGradeDescending: Comparator<Student> =
        compareByDescending { it.grade }

    println(students.sortedWith(byName).map { it.name })
    println(students.sortedWith(byGradeDescending).map { it.name })

}