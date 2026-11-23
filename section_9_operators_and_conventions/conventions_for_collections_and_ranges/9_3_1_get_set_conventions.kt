package kotlin_in_action.section_9_operators_and_conventions.conventions_for_collections_and_ranges

import kotlin_in_action.section_9_operators_and_conventions.Point

// 9.3.1 - Accessing element by index: The get and set conventions

/*
    Indexed accessed operator a[i] - дозволяє отримувати та змінювати елементи за індексом.
    Визначені в колекціях, можна додати і до свого класу
    - a[i] перетворюється на a.get(i) — отримання значення.
    - a[i] = value перетворюється на a.set(i, value) — зміна значення.
    - Функції get() і set() перевантажуються через модифікатор operator.
*/

// Завдання 1: Реалізувати get() для Point через extension-функцію
// та обробити неправильні індекси.
operator fun Point.get(index: Int): Int {
    return when(index) {
        0 -> this.x
        1 -> this.y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

// Завдання 2: Перевантажити get() і set() для MutablePoint,
// щоб отримувати та змінювати координати через [].
class MutablePoint(var x: Int, var y: Int){
    operator fun get(index: Int): Int {
        return when(index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
        }
    }

    operator fun set(index: Int, value: Int) {
        when(index) {
            0 -> x = value
            1 -> y = value
            else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
        }
    }
}

// Завдання 3: Реалізувати get() і set() для IntMatrix
// з доступом за двома індексами та форматованим виведенням.
class IntMatrix(
    private val cells: Array<IntArray>,
){
    operator fun get(row: Int, column: Int): Int = cells[row][column]
    operator fun set(row: Int, column: Int, value: Int) {
        cells[row][column] = value
    }
    override fun toString(): String {
        return cells.joinToString("\n") { row ->
            row.joinToString(" ", "[ ", " ]") { "%3d".format(it) }
        }
    }
}

fun main() {
    println("Task 1")
    val point = Point(10, 20)

    try {
        println(point[0])
        println(point[1])
        println(point[2])
    }catch (e: IndexOutOfBoundsException) {
        println(e.message)
    }


    println("\nTask 2")
    val mutablePoint = MutablePoint(10, 20)

    println(mutablePoint[0])
    println(mutablePoint[1])

    try {
        mutablePoint[0] = 50
        mutablePoint[1] = 100
        mutablePoint[2] = 200
    }catch (e: IndexOutOfBoundsException) {
        println(e.message)
    }
    println(mutablePoint[0])
    println(mutablePoint[1])


    println("\nTask 3")
    val matrix = IntMatrix(
        arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
    )

    println(matrix[0, 1])

    matrix[1, 2] = 99
    println(matrix[1, 2])

    println(matrix)
}