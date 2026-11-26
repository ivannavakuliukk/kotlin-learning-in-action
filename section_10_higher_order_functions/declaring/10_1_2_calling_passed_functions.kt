package kotlin_in_action.section_10_higher_order_functions.declaring

import kotlin_in_action.Employee
import kotlin_in_action.Movie
import kotlin_in_action.employees
import kotlin_in_action.movies
import kotlin_in_action.section_3_functions.joinToString

// 10.1.2 - Calling functions passed as argument

/*
    
*/

// Завдання 1 (з класом Product): Реалізувати функцію обробки зарплат працівників,
// яка приймає та викликає функцію розрахунку нової зарплати.
fun processSalary(employees: List<Employee>, transform: (salary: Int) -> Int){
    employees.forEach{ employee->
        val updatedSalary = transform(employee.salary)
        println("Employee: ${employee.name}: previous salary: ${employee.salary}, updated salary: $updatedSalary")
    }
}

// Завдання 2 (з класом Movie): Реалізувати власну функцію фільтрації фільмів,
// яка приймає предикат та повертає елементи, що його задовольняють.
fun findMovies(movies: List<Movie>, predicate: (movie: Movie) -> Boolean): List<Movie>{
    val matchingFilms: MutableList<Movie> = mutableListOf()
    for (movie in movies) {
        if (predicate(movie)) {
            matchingFilms.add(movie)
        }
    }
    return matchingFilms
}

fun main() {
    println("Task 1")
    println("Salary with bonus")
    processSalary(employees, transform = { salary -> salary + salary * 10/100})
    println("\nSalary with tax")
    processSalary(employees) {salary -> salary - salary * 18/100}


    println("\nTask 2")
    println("Movies with rating more than 8.5:\n" +
            findMovies(movies, predicate = { movie -> movie.rating > 8.5 }).joinToString("\n")
    )
    println("\nSci-Fi movies after 2010:\n" +
            findMovies(movies, predicate = { movie -> movie.genre =="Sci-Fi" && movie.year>2010 }).joinToString("\n"))
}