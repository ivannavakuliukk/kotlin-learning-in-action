package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.Movie
import kotlin_in_action.movies

// 6.1.8 - handling special cases for collections - ifEmpty
/*
    ifEmpty - перевіряє, чи колекція порожня.

    Якщо колекція НЕ порожня → повертає її без змін.
    Якщо колекція порожня → виконує lambda
    і повертає альтернативне значення.

    Важливо:
    значення, яке повертає lambda в ifEmpty,
    має бути того самого типу, що й колекція.
 */

fun main(){
    // Створи функцію, яка приймає список фільмів і повертає назви фільмів із рейтингом 9.0 і вище.
    // Якщо таких фільмів немає — замість порожнього списку виведи: ...
    println("Task 1")
    val checkHighRating =
        { movies: List<Movie> -> movies
            .filter { it.rating >= 9.0 }
            .map(Movie::title)
            .ifEmpty { listOf("No movies found")}
        }
    println(checkHighRating(movies))

    // Створи функцію, яка приймає список фільмів і повертає фільми певного жанру.
    // Перевір її з жанром "Comedy", якого немає у твоєму списку.
    // Якщо результат порожній, виведи: ...
    val moviesByGenre = {
        genre: String, movies: List<Movie> -> movies
        .filter { it.genre == genre }
        .ifEmpty { movies }

    }
    println(moviesByGenre("Comedy", movies))
    println(moviesByGenre("Drama", movies))

}