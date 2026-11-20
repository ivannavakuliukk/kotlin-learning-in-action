package kotlin_in_action.section_3_functions

import kotlin_in_action.Employee
import kotlin_in_action.Movie
import kotlin_in_action.Product

infix fun Employee.earnsMoreThat(other: Employee): Boolean {
    return this.salary > other.salary
}

infix fun Product.costsMoreThan(other: Int): Boolean {
    return price > other
}

infix fun Movie.togetherWith(other: Movie): List<Movie> {
    return listOf(this, other)
}

infix fun Int.percentOf(other: Int): Double {
    return other * (this / 100.0)
}

fun main() {
    println("Task 1")
    val employee1 = Employee("John", 20, 1500, "Unknown")
    val employee2 = Employee("Bob", 30,1000, "unknown")

    println(employee1 earnsMoreThat employee2)
    println(employee2 earnsMoreThat employee1)

    println("\nTask 2")
    val product = Product("Coffee", 350)

    println(product costsMoreThan 200)
    println(product costsMoreThan 500)

    println("\nTask 3")
    val movie1 = Movie("Inception", "Sci-Fi", 2010, 8.8)
    val movie2 = Movie("Interstellar", "Sci-Fi", 2014, 8.7)

    val movies = movie1 togetherWith movie2
    println(movies)

    println("\nTask 4")
    println(20 percentOf 500)
    println(5 percentOf 50)

    // Перевірка пріоритету операцій
    println(10 percentOf 100 + 100)
    println((10 percentOf 100) + 100)
    println(10 percentOf (100 + 100))
}