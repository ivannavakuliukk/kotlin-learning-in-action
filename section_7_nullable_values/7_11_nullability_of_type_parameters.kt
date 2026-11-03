package kotlin_in_action.section_7_nullable_values

import kotlin_in_action.products

// 7.11 - nullability of type parameters

/*
    За замовчуванням generic-параметр T може приймати як nullable, так і non-nullable типи.
    Щоб заборонити null треба використати обмеження <T:Any>

    T? як результат означає, що функція може повернути значення типу T або null.
 */

// Завдання 1
// Створи generic-функцію getFirstOrNull(), яка приймає List<T> і повертає перший елемент списку.
// Якщо список порожній повертає null. Елементи списку можуть бути nullable.
fun <T> findOrNull(list: List<T>, index: Int): T?{
    return when{
        list.size < index + 1 -> null
        index < 0 -> null
        else -> list[index]
    }
}

// Завдання 2
// Створити клас Кеш, який зберігає одне значення non-nullable уд якого типу.
// Клас повинен дозволяти: зберегти значення; отримати збережене значення;
// очистити значення, після чого результат отримання має бути null.
class Cache<T:Any>(value: T){
    var cachedValue: T? = value

    fun getValue(): T? = cachedValue
    fun removeValue() {
        cachedValue = null
    }
}

fun main(){
    println("Task 1")
    val numberList = listOf(1,2,3,4,5)
    val stringList = listOf("one","two","three", null)
    val productList = products
    val emptyList = emptyList<Int?>()

    println(findOrNull(numberList, 5))
    println(findOrNull(stringList, 3))
    println(findOrNull(productList, 1))
    println(findOrNull(emptyList, 0))

    println("\nTask 2")
    val cache = Cache(numberList)
    println(cache.getValue())
    cache.removeValue()
    println(cache.getValue())
}