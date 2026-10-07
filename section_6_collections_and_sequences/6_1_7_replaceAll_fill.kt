package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.Movie

// 6.1.7 - replacing elements in mutable collection: replaceAll, fill

/*
    replaceAll - замінює кожен елемент MutableList
    на результат виконання заданої lambda.
    Змінює існуючий список і нічого не повертає (Unit).

    fill - замінює всі елементи MutableList
    на одне задане значення.
    Змінює існуючий список і нічого не повертає (Unit).

    subList - повертає view на частину оригінального списку
    в заданому діапазоні індексів.
    Початковий індекс входить, кінцевий — не входить.
    subList не створює копію списку.
    Зміни через subList змінюють оригінальний список.
 */

val favouriteMovies = mutableListOf(
    Movie("Inception", "Sci-Fi", 2010, 8.8),
    Movie("Interstellar", "Sci-Fi", 2014, 8.7),
    Movie("The Dark Knight", "Action", 2008, 9.9),
    Movie("Gladiator", "Action", 2000, 8.5),
    Movie("The Green Mile", "Drama", 1999, 8.6),
    Movie("Forrest Gump", "Drama", 1994, 8.8),
    Movie("Dune", "Sci-Fi", 2021, 8.0),
    Movie("Parasite", "Drama", 2019, 8.5)
)

fun main(){
    // Збільш рейтинг кожного фільму на 0.2.
    // Якщо рейтинг після зміни перевищує 10.0, встанови його рівно 10.0. Виведи оновлений список
    println("Task 1")
    favouriteMovies.replaceAll{
        it.copy(
            rating = if(it.rating + 0.2 > 10.0 ) 10.0 else it.rating + 0.2,
        )
    }
    println(favouriteMovies)

    // Заміни фільми з індексів 2 до 4 включно на один і той самий фільм Movie("Coming Soon", "Unknown", 2026, 0.0)
    println("Task 2")
    favouriteMovies
        .subList(2, 5)
        .fill(Movie("Coming Soon", "Unknown", 2026, 0.0))
    println(favouriteMovies)

    // Замініть всі фільми в списку на один фільм Movie("Unknown", "Unknown", 0, 0.0).
    println("Task 3")
    favouriteMovies
        .fill(Movie("Unknown", "Unknown", 0, 0.0))
    println(favouriteMovies)
}