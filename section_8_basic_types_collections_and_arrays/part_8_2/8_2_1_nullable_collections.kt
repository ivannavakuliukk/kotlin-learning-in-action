package kotlin_in_action.section_8_basic_types_collections_and_arrays.part_8_2

import kotlin_in_action.Product

// 8.2.1 - collections of nullable values and nullable collections

/*
    Kotlin підтримує nullable type arguments, тому елементи колекції можуть мати nullable-тип,
    а сама колекція також може бути nullable.
 */

// Завдання 1 — Обчисли середню оцінку зі списку, ігноруючи null.
fun calculateAverageRating(ratings: List<Int?>): Double?{
    val filteredRating = ratings.filterNotNull()
    return if(filteredRating.isEmpty()) null
    else filteredRating.average()
}

// Завдання 2 (з класом Product) — Обчисли загальну вартість товарів із nullable-списку.
fun calculateInventoryValue(products: List<Product>?): Int{
    return if(products.isNullOrEmpty()) 0
    else products.sumOf { it.price }
}

fun main(){
    println("Task 1")
    val ratings: List<Int?> = listOf(5, null, 4, 3, null, 5)
    println(calculateAverageRating(ratings))
    println(calculateAverageRating(listOf(null, null)))

    println("\nTask 2")
    println(calculateInventoryValue(listOf(
        Product("Laptop", 40000),
        Product("Mouse", 800)
    )))
    println(calculateInventoryValue(emptyList()))
    println(calculateInventoryValue(null))
}