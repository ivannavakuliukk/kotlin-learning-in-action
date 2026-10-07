package kotlin_in_action.section_7_nullable_values

// 7.6 - Safely casting values without throwing exceptions: as?

/*
    Safe-cast operator (as?) — безпечно приводить об'єкт до вказаного типу.
    Якщо приведення неможливе, повертає null замість помилки.
*/

// Перевизначити equals функцію, використовуючи as?
class Book(
    val title: String,
    val author: String
){
    override fun equals(other: Any?): Boolean {
        val otherBook = other as? Book ?: return false
        return otherBook.title == title && otherBook.author == author
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }
}

// Перевизначити equals функцію, використовуючи is
class Cat(
    val name: String,
    val age: Int
){
    override fun equals(other: Any?): Boolean {
        return if(other is Cat) other.name == name && other.age == age else false
    }

    override fun hashCode(): Int {
        var result = age
        result = 31 * result + name.hashCode()
        return result
    }
}

fun main(){
    println("Task 1")
    val book1 = Book("Harry Potter", "J.K.Rowling")
    val book2 = Book("Harry Potter", "J.K.Rowling")
    val book3 = Book("Twilight", "Stephenie Meyer")

    println(book1 == book2)
    println(book1 == book3)

    println("\nTask 2")
    val cat1 = Cat("Blacky", 11)
    val cat2 = Cat("Blacky", 22)
    val cat3 = Cat("Blacky", 11)
    println(cat1 == cat2)
    println(cat1 == cat3)
}

