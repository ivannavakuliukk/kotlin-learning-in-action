package kotlin_in_action.section_9_operators_and_conventions.overloading_arithmetic_operators

import kotlin_in_action.section_9_operators_and_conventions.CartItem
import kotlin_in_action.section_9_operators_and_conventions.Money
import kotlin_in_action.section_9_operators_and_conventions.Point

// 9.1.1 - Plus, times, divide, and more: Overloading binary arithmetic operations

/*
    Operator overloading (перевантаження операторів) — механізм Kotlin, який дозволяє визначати поведінку стандартних операторів
    для власних типів за допомогою функцій-конвенцій із модифікатором operator.
    Бінарні арифметичні операції — операції з двома операндами: + (plus), - (minus), * (times), / (div), % (rem).
    - Функції можуть бути методами класу або extension-функціями.
    - Типи операндів і результату можуть відрізнятися.
    - Комутативність (зміна операндів місцями) не забезпечується автоматично.

    Сумісність з джава:
    1) У Java операторні функції Kotlin викликаються як звичайні методи(за назвою).
    2) У Kotlin Java-методи можна використовувати як оператори, якщо їхні назви та сигнатури відповідають операторним конвенціям.
     Модифікатор operator для них не потрібен
*/

// Завдання 1: Перевантажити бінарні арифметичні оператори +, -, *, / для Point.
// Реалізувати операторні конвенції через extension-функції.
operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.minus(other: Point): Point {
    return Point(x - other.x, y - other.y)
}

operator fun Point.times(other: Int):Point = Point(x * other, y * other)

operator fun Point.div(other: Int): Point = Point(x/ other, y/ other)

// Завдання 2: Перевантажити оператори +, -, *, /, % для Money через методи класу.
// -> У Money.kt

// Завдання 3: Забезпечити комутативність множення Point та Int.
// Перевантажити множення товару на кількість із поверненням іншого типу (Money).
operator fun Int.times(other: Point): Point = other * this
operator fun CartItem.times(other: Int): Money = price * other

fun main() {
    println("Task 1")
    val p1 = Point(10, 20)
    val p2 = Point(2, 4)

    println(p1 + p2)
    println(p1 - p2)
    println(p1 * 2)
    println(p1 / 2)


    println("\nTask 2")
    val m1 = Money(1000)
    val m2 = Money(500)

    println(m1 + m2)
    println(m1 - m2)
    println(m1 * 3)
    println(m1 / 3)
    println(m1 % 3)


    println("\nTask 3")
    val point = Point(10, 20)

    println(point * 2)
    println(2 * point)

    val product = CartItem("Coffee", Money(15000))
    println(product * 3)

    // Перевірити сумісність операторних конвенцій Kotlin та Java:
    // викликати Kotlin-оператори з Java та Java-методи як оператори в Kotlin.
    // -> OperatorExampleMoney, OperatorTest
    println("\nTask 4")
    val first = OperatorExampleMoney(1000)
    val second = OperatorExampleMoney(500)

    println((first + second).amount)
    println((first - second).amount)
}