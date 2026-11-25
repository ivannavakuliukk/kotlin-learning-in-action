package kotlin_in_action.section_9_operators_and_conventions.destructing_declarations

import kotlin_in_action.employees

// 9.4.1 - Destructing declarations and loops

/*
    Деструктуризація дозволяє отримувати компоненти об'єктів безпосередньо під час перебору колекцій.
    - for ((a, b) in collection) — деструктуризація кожного елемента в циклі for.
    - collection.forEach { (a, b) -> ... } — деструктуризація параметра лямбди.
    - Для Map.Entry<K, V> стандартна бібліотека надає extension-функції component1() та component2(), які повертають ключ і значення відповідно.
*/

fun main() {
    // Завдання 1: Використати деструктуризацію Employee
    // безпосередньо в циклі for
    println("Task 1")
    for((name, age, salary, department) in employees){
        println("$name, $age years has $salary$ at $department")
    }

    // Завдання 2: Використати деструктуризацію Map.Entry
    // у параметрі лямбди forEach для отримання ключа та значення
    println("\nTask 2")
    val products = mapOf(
        "Coffee" to 120,
        "Tea" to 80,
        "Chocolate" to 95
    )
    products.forEach{(key, value)->
        println("$key is $value$")
    }
}