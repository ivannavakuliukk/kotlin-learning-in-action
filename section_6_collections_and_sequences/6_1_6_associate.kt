package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.movies

// 6.1.6 - transforming collections into maps: associate, associateWith, associateBy

/*
    associate - перетворює колекцію на Map,
    дозволяючи самостійно визначити і ключ, і значення (to у лямбді).

    associateBy - створює Map, де ключ визначається
    з елемента колекції (використовуючи передану лямбду), а значенням є сам елемент.

    associateWith - створює Map, де ключем є сам елемент
    колекції, а значення визначається використовуючи передану лямбду.
 */

fun main() {
    // Створи Map, де назва фільму є ключем, а його рейтинг — значенням.
    println("Task 1")
    val nameToRating = movies.associate{ it.title to it.rating }
    println(nameToRating)

    // Створи Map, де рік виходу фільму є ключем, а сам об'єкт Movie — значенням.
    println("Task 2")
    val moviesByYear = movies.associateBy{ it.year }
    println(moviesByYear)

    // Створи Map, де об'єкт Movie є ключем, а значенням буде true або false залежно від того,
    // чи має фільм рейтинг 8.5 або вище.
    println("Task 3")
    val moviesWithIsHighRating = movies.associateWith{ it.rating >= 8.5 }
    println(moviesWithIsHighRating)
}