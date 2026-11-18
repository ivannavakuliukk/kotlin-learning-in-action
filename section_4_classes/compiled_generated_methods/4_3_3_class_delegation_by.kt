package kotlin_in_action.section_4_classes.compiled_generated_methods

// 4.3.3 - Class delegation: Using the by keyword

/*
    Class Delegation — механізм Kotlin, який дозволяє автоматично делегувати реалізацію методів інтерфейсу іншому об'єкту за допомогою by.
    Decorator — структурний патерн проєктування, який дозволяє розширювати поведінку об'єкта без зміни його класу.
    Декоратор реалізує той самий інтерфейс, обгортає об'єкт і додає власну поведінку.
    Kotlin спрощує реалізацію Decorator через by: потрібні методи перевизначаються, а решта автоматично делегується обгорнутому об'єкту.
*/

// Завдання 1: Реалізувати декоратор LoggingSender через делегування MessageSender за допомогою by.
// Додати логування до send(), залишивши getSentCount() автоматично делегованим.
interface MessageSender{
    fun send(message: String)
    fun getSentCount(): Int
}

class EmailSender: MessageSender {
    private var sentCount = 0
    override fun send(message: String) {
        sentCount++
        println("Message: $message")
    }
    override fun getSentCount(): Int {
        return sentCount
    }
}

class LoggingSender(
    private val innerSender: MessageSender = EmailSender(),
): MessageSender by innerSender {
    override fun send(message: String) {
        println("Sending message...")
        innerSender.send(message)
        println("Message sent!")
    }
}

// Завдання 2: Створити універсальний декоратор LoggingList<T> для MutableList<T> через by.
// Перевизначити add() і remove() для логування, перевірити делеговані методи та addAll().
class LoggingList<T>(
    private val innerList: MutableList<T> = mutableListOf<T>()
): MutableList<T> by innerList{
    override fun add(element: T): Boolean {
        println("Adding element: $element")
        return innerList.add(element)
    }

    override fun remove(element: T): Boolean {
        println("Removing element: $element")
        return innerList.remove(element)
    }
}

fun main() {
    println("Task 1")
    val sender = LoggingSender()
    sender.send("Hello")
    sender.send("World")
    println(sender.getSentCount())


    println("\nTask 2")
    val loggingList = LoggingList(mutableListOf("Kotlin", "Java"))

    loggingList.add("Python")
    loggingList.remove("Java")

    println(loggingList.size)
    println(loggingList.contains("Kotlin"))

    loggingList.addAll(listOf("Go", "Rust"))
    println(loggingList.joinToString(", "))
}