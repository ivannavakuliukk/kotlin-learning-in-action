package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.Movie
import kotlin_in_action.movies

// 6.1.5 - converting a list to a map of groups - groupBy

/*
    groupBy - групує елементи колекції за певною ознакою.
    Повертає Map, де ключ — значення, за яким групуємо,
    а value — список елементів цієї групи.
 */

fun main(){
    // Згрупуй фільми за жанром. Для кожного жанру виведи кількість фільмів та середній рейтинг.
    println("Task 1")
    val moviesByGenre = movies.groupBy{it.genre}
    moviesByGenre.forEach { (genre, movies) ->
        println("$genre: count - ${movies.size}, " +
                "average rating - ${"%.2f".format(movies.map(Movie::rating).average())}")

    }

    // Згрупуй фільми за жанром, а всередині кожної групи залиш тільки фільми з рейтингом 8.5 і вище.
    // Виведи назви таких фільмів для кожного жанру.
    println("\nTask 2")
    val moviesByGenreHighRating = moviesByGenre.mapValues { movies -> movies.value.filter{ it.rating >= 8.5} }
    moviesByGenreHighRating.forEach { (genre, movies) ->
        println("$genre: ${movies.joinToString { it.title }}")
    }

    // Для кожного жанру знайди фільм з найвищим рейтингом та виведи його назву і рейтинг.
    println("\nTask 3")
    val higherRatingMovies = moviesByGenre.mapValues { entry ->
        entry.value.maxByOrNull { it.rating }
    }
    higherRatingMovies.forEach { (genre, movie) ->
        println("$genre: ${movie?.title} ${movie?.rating}")
    }
}