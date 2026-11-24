package kotlin_in_action.section_9_operators_and_conventions.conventions_for_collections_and_ranges

import kotlin_in_action.section_9_operators_and_conventions.CartItem
import kotlin_in_action.section_9_operators_and_conventions.Money
import kotlin_in_action.section_9_operators_and_conventions.ShoppingCart
import java.time.LocalDate

// 9.3.4 - Making it possible to loop over your types: The iterator convention

/*
    Конвенція operator iterator() дозволяє використовувати цикл for для перебору елементів власних об'єктів.
    - Зазвичай iterator() повертає об'єкт, який реалізує інтерфейс Iterator<T>,
     для цього краще використовувати object declaration, щоб перевизначити методи.
    - Iterator<T> визначає два основні методи:
      - hasNext() — перевіряє наявність наступного елемента, не змінюючи стан ітератора.
      - next() — повертає наступний ще не отриманий елемент і пересуває ітератор далі.
*/

// Завдання 1: Реалізувати iterator() для ShoppingCart,
// делегуючи перебір готовому ітератору списку товарів.
operator fun ShoppingCart.iterator(): Iterator<CartItem> = getItems().iterator()

// Завдання 2: Реалізувати власний Iterator<LocalDate>
// для послідовного перебору дат закритого діапазону.
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate>{
    return object: Iterator<LocalDate> {
        private var currentDate = start
        override fun next(): LocalDate {
            if (!hasNext()) throw NoSuchElementException()
            val thisDate = currentDate
            currentDate = currentDate.plusDays(1)
            return thisDate
        }

        override fun hasNext(): Boolean {
            return currentDate <= endInclusive
        }

    }
}

fun main() {
    println("Task 1")
    val shoppingCart = ShoppingCart(
        mutableListOf(
            CartItem("Coffee", Money(25)),
            CartItem("Pineapple", Money(3)),
            CartItem("Orange", Money(4)),
            CartItem("Tea", Money(10))
        )
    )
    for(item in shoppingCart) {
        println("Item ${item.name} costs ${item.price.amount}$")
    }

    println("\nTask 2")
    val newYear = LocalDate.ofYearDay(2023, 1)
    val daysOff = newYear.minusDays(3) .. newYear
    for(dayOff in daysOff) {
        println(dayOff)
    }
}