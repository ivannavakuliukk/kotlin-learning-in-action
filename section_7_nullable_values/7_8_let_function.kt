package kotlin_in_action.section_7_nullable_values

import kotlin_in_action.Movie
import kotlin_in_action.User
import kotlin_in_action.movies
import kotlin_in_action.users

// 7.8 - Dealing with nullable expressions: the let function

/*
    let — scope функція, повертає результат лямбди, доступ до об'єкта всередині через it.
    Часто використовується з ?.(safe-call), щоб виконати блок лише тоді, коли nullable-значення не дорівнює null;
 */

/*
    Додатково у завданні використано:
    labeled this(this@) - щоб вказати до якого receiver звертаємось,
    buildList для створення списку,
    extension функції класу
 */

// Завдання 1 (з класом User):
// Реалізуй невелику систему формування профілів користувачів на основі списку users.
// Система має формувати короткий опис користувача лише тоді, коли його city не дорівнює null.

fun List<User>.toProfiles(): List<String>{
    return buildList {
        this@toProfiles.forEach { user->
            user.city?.let{
                add("User description: ${user.name} from $it")
            }
        }
    }
}

// Завдання 2 (з класом Movie)
// Реалізуй пошук фільму за назвою над готовим списком movies
// Оброби результат пошуку так, щоб за наявності фільму сформувати інформацію для його картки.
// Якщо фільм не знайдено, картка не повинна формуватися.

fun List<Movie>.findMovie(movieName: String): String {
    return this.firstOrNull { it.title.equals(movieName, ignoreCase = true) }?.let { movie->
        "Name: ${movie.title}, genre: ${movie.genre}, rating: ${movie.rating}"
    } ?: "Movie not found"
}


fun main(){
    println("Task 1")
    println(users.toProfiles().joinToString("\n"))

    println("\nTask 2")
    println(movies.findMovie("Inception"))
    println(movies.findMovie("Harry Potter"))
}
