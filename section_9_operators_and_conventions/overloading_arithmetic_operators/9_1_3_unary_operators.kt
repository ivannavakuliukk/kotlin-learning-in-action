package kotlin_in_action.section_9_operators_and_conventions.overloading_arithmetic_operators

import kotlin_in_action.section_9_operators_and_conventions.CartItem
import kotlin_in_action.section_9_operators_and_conventions.Money
import kotlin_in_action.section_9_operators_and_conventions.Point
import kotlin_in_action.section_9_operators_and_conventions.ShoppingCart

// 9.1.3 - Operators with only one operand: Overloading unary operators

/*
    Unary operators (унарні оператори) — оператори, які працюють з одним операндом.
    - Перевантажуються через функції-конвенції unaryPlus() (+), unaryMinus() (-), not() (!), inc() (++), dec() (--).
    - Операторні функції не приймають параметрів.
    - inc() та dec() повинні повертати значення того самого типу або його підтипу, не змінюючи початковий об'єкт.
      Оператори ++ та -- переприсвоюють змінну, тому потребують var.
    - Префіксна форма повертає нове значення, постфіксна — попереднє. Окремо перевантажувати їх не потрібно.
*/

// Завдання 1: Перевантажити unaryPlus(), unaryMinus(), inc() та dec()
// для Point. Дослідити префіксну та постфіксну форми ++ і --.
operator fun Point.unaryPlus(): Point {
    return this
}

operator fun Point.unaryMinus(): Point{
    return Point(-this.x, -this.y)
}

operator fun Point.inc(): Point {
    return Point(this.x + 1, this.y + 1)
}

operator fun Point.dec(): Point {
    return Point(this.x - 1, this.y - 1)
}

// Завдання 2: Перевантажити оператор not() для ShoppingCart,
// який перевіряє, чи є кошик порожнім.
// -> В CartItem.kt

fun main() {
    println("Task 1")
    var point = Point(-10, 20)

    println(+point)
    println(-point)

    println("Postfix: ${point++}")
    println("Current: $point")

    println("Prefix: ${++point}")
    println("Current: $point")

    println("Postfix: ${point--}")
    println("Current: $point")

    println("Prefix: ${--point}")
    println("Current: $point")

    println("\nTask 2")
    val cart = ShoppingCart()
    println(!cart)

    val coffee = CartItem("Coffee", Money(15000))
    cart += coffee
    println(!cart)
}