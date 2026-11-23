package kotlin_in_action.section_9_operators_and_conventions.overloading_comparisons_operators

// 9.1.2 - Equality operators: equals(==)

/*
    Equality operators (оператори рівності) — використовуються для порівняння об'єктів.
    - == / != — структурна рівність, перевіряється через equals().
    - === / !== — референсна рівність, перевіряють, чи посилання вказують на той самий об'єкт. Не перевантажуються.
    - == безпечно працює з null: a == b еквівалентне a?.equals(b) ?: (b === null).
    - equals() перевизначається через override fun equals(other: Any?): Boolean, а не через extension-функцію.
*/

// Завдання: Самостійно перевизначити equals() та hashCode() для Book.
// Перевірити структурну (==, !=) і референсну (===, !==) рівність та порівняння з null.
class Book(val title: String, val author: String) {

    override fun equals(other: Any?): Boolean {
        if(other === this)  return true
        if(other !is Book) return false
        return title == other.title && author == other.author
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }
}

fun main() {
    val first = Book("Kotlin in Action", "Dmitry Jemerov")
    val second = Book("Kotlin in Action", "Dmitry Jemerov")
    val third = first
    val different = Book("Effective Kotlin", "Marcin Moskala")

    println(first == second)
    println(first != different)
    println(first === second)
    println(first === third)
    println(first !== second)

    val nullable: Book? = null
    println(first == nullable)
    println(nullable == null)
}