package kotlin_in_action.section_9_operators_and_conventions.overloading_arithmetic_operators

import kotlin_in_action.section_9_operators_and_conventions.CartItem
import kotlin_in_action.section_9_operators_and_conventions.Money
import kotlin_in_action.section_9_operators_and_conventions.ShoppingCart

// 9.1.2 - Applying an operation and immediately assigning its value: Overloading compound assignment operators

/*
    Compound assignment operators (складені оператори присвоєння) — оператори +=, -=, *=, /=, %=,
    які в Kotlin можуть працювати двома способами:
    - Через звичайні операторні функції (plus(), minus() тощо): a += b перетворюється на a = a.plus(b).
     Потрібен var, оскільки змінна переприсвоюється.
    - Через спеціальні функції-конвенції (plusAssign(), minusAssign(), timesAssign(), divAssign(), remAssign()):
     a += b перетворюється на a.plusAssign(b). Функції повертають Unit і зазвичай змінюють стан об'єкта, тому працюють
     як із val, так і з var.
    - Якщо обидва способи одночасно застосовні, виникає неоднозначність (assignment operators ambiguity).
    - Для read-only List використовується переприсвоєння нового списку, а для MutableList —
     зміна вмісту наявного списку (коли застосовується *Assign()).

    Загальне правило: Варто уникати одночасного визначення обох варіантів для однакових типів операндів, щоб не створювати неоднозначності:
    -> Для immutable класів — plus(), minus() тощо, які повертають нове значення.
    -> Для mutable класів — plusAssign(), minusAssign() тощо, які змінюють стан наявного об'єкта.
    */

// Завдання 1: Використати складені оператори присвоєння для Money
// на основі вже перевантажених бінарних арифметичних операторів.


// Завдання 2: Реалізувати plusAssign() та minusAssign() для ShoppingCart.
// Дослідити зміну стану об'єкта та конфлікт між plus() і plusAssign().
// -> y CartItem.kt

// Завдання 3: Дослідити роботу складених операторів присвоєння
// для List та MutableList, порівнявши ідентичність посилань через ===.
fun observeCollectionOperators(){
    var readOnlyList = listOf(1, 2, 3)
    val mutableList = mutableListOf(1, 2, 3)

    val originalReadOnly = readOnlyList
    val originalMutable = mutableList

    readOnlyList += 4
    mutableList += 5

    println("Read-only collections, if the same reference: ${readOnlyList === originalReadOnly}")
    println("Mutable collections, if the same reference: ${mutableList === originalMutable}")
}

fun main() {
    println("Task 1")
    // Якщо буде val код не виконається - оператори змінюють змінну
    var money = Money(1000)

    money += Money(500)
    println(money)
    money -= Money(200)
    println(money)
    money *= 2
    println(money)
    money /= 4
    println(money)
    money %= 3
    println(money)

    println("\nTask 2")
    val cart = ShoppingCart()
    val coffee = CartItem("Coffee", Money(15000))
    val tea = CartItem("Tea", Money(8000))

    cart += coffee
    cart += tea
    println(cart.getItems())

    cart -= coffee
    println(cart.getItems())

    println("\nTask 3")
    observeCollectionOperators()
}