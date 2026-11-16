package kotlin_in_action.section_8_basic_types_collections_and_arrays.collections_and_arrays

import java.util.Arrays

// 8.2.5 - Creating arrays of objects and primitive types for interoperability and performance reasons

/*
    У Kotlin масиви використовують переважно для взаємодії з Java, передачі аргументів у vararg та ефективної роботи з примітивами.
    - Створення Array<T>: arrayOf(), arrayOfNulls<T>(size), Array(size) { ... }.
    - Примітивні масиви: IntArray, ByteArray тощо. Створюються через intArrayOf(), IntArray(size) або IntArray(size) { ... }.
    - Перетворення: toTypedArray() — колекція → Array<T>, toIntArray() — колекція або Array<Int> → IntArray, toList() — масив → список.
    - vararg: дозволяє передавати довільну кількість аргументів, а оператор * розгортає масив під час виклику.
    - Операції: масиви підтримують filter, map та інші операції, як і колекції. Багато з них повертають List, а не масив.
 */

data class Item(
    val name: String,
    val weight: Int
)

// Завдання 1: Створити масиви предметів трьома способами.
// Заповнити рюкзак стартовими предметами та порахувати зайняті слоти.
fun task1Inventory() {
    println("Task 1")
    val starterKit = arrayOf(
        Item("Wooden Sword", 3),
        Item("Leather Armor", 5),
        Item("Health Potion", 1)
    )
    val backpack = arrayOfNulls<Item>(8)
    val loot = Array(5) { i -> Item("Loot #$i", i * 2) }

    starterKit.forEachIndexed { index, item ->
        backpack[index] = item
    }

    println("Filled backpack slots: ${backpack.count { it != null }}")
    println("Loot: ${loot.contentToString()}")
}

// Завдання 2: Відсортувати рекорди через Java Arrays, знайти результат
// бінарним пошуком та отримати топ-3 без використання List.
fun task2Leaderboard() {
    println("\nTask 2")
    val scores = intArrayOf(420, 150, 980, 310, 770)

    Arrays.sort(scores)
    val desiredResult = Arrays.binarySearch(scores, 770)

    val top3 = scores.copyOfRange(scores.size - 3, scores.size)
    top3.reverse()

    println("Sorted scores: ${Arrays.toString(scores)}")
    println("770 at index $desiredResult")
    println("Top 3: ${top3.contentToString()}")
}

// Завдання 3: Створити IntArray трьома способами та заповнити значеннями.
// Перетворити List<Int> і Array<Int> у примітивні масиви та назад.
fun task3EnemyHealth() {
    println("\nTask 3")
    val hpArray1 = intArrayOf(500, 400, 500, 500, 800)
    val hpArray2 = IntArray(5)
    val hpArray3 = IntArray(5) { (it + 1) * 100 }

    hpArray2.fill(250)

    println("HP 1: ${hpArray1.contentToString()}")
    println("HP 2: ${hpArray2.contentToString()}")
    println("HP 3: ${hpArray3.contentToString()}")

    val hpList = listOf(100, 200, 300)
    val hpBoxed = arrayOf(100, 200, 300)

    val hpFromList = hpList.toIntArray()
    val hpFromBoxed = hpBoxed.toIntArray()

    val listFromHp = hpFromList.toList()
    val boxedFromHp = hpFromBoxed.toTypedArray()

    println("From List: ${hpFromList.contentToString()}")
    println("From Array: ${hpFromBoxed.contentToString()}")
    println("Back to List: $listFromHp")
    println("Back to Array: ${boxedFromHp.contentToString()}")
}

fun logEvents(vararg events: String) {
    events.forEachIndexed { index, event ->
        println("${index + 1}: $event")
    }
}

// Завдання 4: Передати аргументи у vararg напряму, з List і Array.
// Використати spread (*) і toTypedArray() для виклику Java String.format().
fun task4EventLogger() {
    println("\nTask 4")
    logEvents("start", "hit", "heal")

    val events = listOf("login", "delete account")
    logEvents(*events.toTypedArray())

    val arr = arrayOf("hit", "heal")
    logEvents(*arr)

    val args: List<Any> = listOf("Hero", "Dragon", 150)
    val message = String.format(
        "%s hit %s for %d",
        *args.toTypedArray()
    )

    logEvents(message)
}

fun main() {
    task1Inventory()
    task2Leaderboard()
    task3EnemyHealth()
    task4EventLogger()
}
