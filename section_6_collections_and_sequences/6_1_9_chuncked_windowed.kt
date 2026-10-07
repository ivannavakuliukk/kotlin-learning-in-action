package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.Movie
import kotlin_in_action.movies
import kotlin.math.abs

// 6.1.9 - Splitting collections - chunked and windowed

/*
    chunked - розділяє колекцію на окремі групи заданого розміру.
    Групи НЕ перекриваються.
    Якщо елементів не вистачає для останньої групи,
    вона просто буде меншого розміру.

    chunked(3) → [1, 2, 3], [4, 5, 6], [7]


    windowed - створює вікна заданого розміру,
    які рухаються по колекції.
    За замовчуванням кожне наступне вікно зміщується на 1 елемент,
    тому вікна ПЕРЕКРИВАЮТЬСЯ.

    windowed(3) → [1, 2, 3], [2, 3, 4], [3, 4, 5]
*/

fun main(){
    // Розділити movies на групи по 3. Для кожної групи вивести назви фільмів і середній рейтинг.
    println("Task 1")
    val chunkedMovies = movies.chunked(3)
    chunkedMovies.forEach{
        val averageRating = it.map(Movie::rating).average()
        println(it.joinToString { it.title } + "- average rating: ${"%.2f".format(averageRating)} ")
    }

    // Розділити movies на пари по 2. Для кожної пари вивести фільм із вищим рейтингом.
    // Останній фільм теж обробити, якщо він залишиться без пари.
    println("\nTask 2")
    val chunkedMovies2 = movies.chunked(2)
    chunkedMovies2.forEach{
        val higherRatingMovie = it.maxBy(Movie::rating)
        println(higherRatingMovie.title + " " + higherRatingMovie.rating)
    }

    // Створити вікна по 3 фільми. Для кожного вікна порахувати середній рейтинг
    // і знайти трійку з найвищим середнім рейтингом.
    println("\nTask 3")
    val windowedMovies = movies.windowed(3)
    val windowedMoviesWithAverageRating = windowedMovies.associateBy{
        val rating = it.map(Movie::rating).average()
        rating
    }
    windowedMoviesWithAverageRating.forEach{
        println(it.value.joinToString { it.title }+ " - average rating ${"%.2f".format(it.key)} ")
    }
    println("The higher rated window - ${windowedMoviesWithAverageRating.maxBy { it.key }.value.map { it.title }}")

    // Створити вікна по 2 фільми. Вивести пари, де різниця рейтингів становить
    // 0.2 або більше, та показати цю різницю.
    println("\nTask 4")
    val windowedMovies2 = movies.windowed(2)
    val windowedMoviesDiffRating = windowedMovies2.associateWith {
        val difference = abs( it[0].rating - it[1].rating)
        difference
    }
    windowedMoviesDiffRating.forEach{
        if(it.value >= 0.2){
            println("%.2f".format(it.value) + " - " + it.key.joinToString { it.title })
        }
    }

}
