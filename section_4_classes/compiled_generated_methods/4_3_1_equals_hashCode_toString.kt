package kotlin_in_action.section_4_classes.compiled_generated_methods

import kotlin_in_action.section_3_functions.joinToString

// 4.3.1 - Universal object methods

/*
У Kotlin всі класи успадковують від Any три основні методи, які можна перевизначати:
    - equals() — визначає логіку порівняння об'єктів через ==. За замовчуванням порівнює ідентичність екземплярів, а не значень властивостей.
    - hashCode() — повертає хеш-код об'єкта. Використовується в HashSet і HashMap. Якщо об'єкти рівні за equals(),
      їхні хеш-коди обов'язково мають збігатися.
    - toString() — повертає текстове представлення об'єкта. За замовчуванням це назва класу та хеш-подібний ідентифікатор.
 */

// Завдання 1: Створити звичайний клас Student і перевизначити equals() та hashCode(),
// щоб студенти порівнювалися за id. Перевірити ==, === та унікальність елементів у HashSet.
class Student(val id: Int, val name: String){
    override fun equals(other: Any?): Boolean {
        return if(other is Student){
            id == other.id
        }else false
    }

    override fun hashCode(): Int {
        return id
    }
}

// Завдання 2: Створити клас LibraryBook і перевизначити toString() для зручного виведення книг.
// Створити список книг і вивести їх через joinToString().
class LibraryBook(val title: String, val author: String, val year: Int){
    override fun toString(): String {
        return "\"$title\" by $author ($year)"
    }
}

fun main(){
    println("Task 1")
    val student1 = Student(1, "John")
    val student2 = Student(1, "Johny")
    val student3 = Student(2, "Mary")

    println(student1 == student2)
    println(student1 === student2)

    println(student1.hashCode())
    println(student2.hashCode())

    val studentSet: HashSet<Student> = hashSetOf(student1, student2, student3)
    println("HashSet size: ${studentSet.size}")


    println("\nTask 2")
    val books = listOf(
        LibraryBook("1984", "George Orwell", 1949),
        LibraryBook("The Hobbit", "J.R.R. Tolkien", 1937),
        LibraryBook("Fahrenheit 451", "Ray Bradbury", 1953)
    )
    println(books.joinToString("; "))
}