package kotlin_in_action.section_9_operators_and_conventions.conventions_for_collections_and_ranges

import kotlin_in_action.section_9_operators_and_conventions.CartItem
import kotlin_in_action.section_9_operators_and_conventions.Money
import kotlin_in_action.section_9_operators_and_conventions.Point
import kotlin_in_action.section_9_operators_and_conventions.Rectangle
import kotlin_in_action.section_9_operators_and_conventions.ShoppingCart

// 9.3.2 - Checking whether the object belongs to collection: The in convention

/*
    Оператор in дозволяє перевіряти належність елемента до колекції, діапазону або власного об'єкта.
    - a in b перетворюється на b.contains(a).
    - a !in b перетворюється на !b.contains(a).
    - Для перевантаження оператора використовується operator fun contains(), яка повинна повертати Boolean.
*/

// Завдання 1: Перевантажити contains() для Rectangle,
// перевіряючи належність точки через напіввідкриті діапазони.
operator fun Rectangle.contains(point: Point): Boolean{
    return point.x in upperLeft.x ..< lowerRight.x &&
            point.y in upperLeft.y ..< lowerRight.y
}

// Завдання 2: Перевантажити contains() для ShoppingCart,
// перевіряючи наявність товару за назвою.
operator fun ShoppingCart.contains(itemName: String): Boolean{
    return getItems().any{itemName == it.name}
}

fun main() {
    println("Task 1")
    val rect = Rectangle(Point(0, 0), Point(5, 5))
    println(Point(2, 3) in rect)
    println(Point(5, 3) in rect)
    println(Point(2, 5) !in rect)

    println("\nTask 2")
    val cart = ShoppingCart()
    cart += CartItem("Apple", Money(1500))
    cart += CartItem("Banana", Money(2000))

    println("Apple" in cart)
    println("Orange" in cart)
    println("Banana" !in cart)
}