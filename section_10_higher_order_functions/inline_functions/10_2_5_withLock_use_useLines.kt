package kotlin_in_action.section_10_higher_order_functions.inline_functions

import kotlin_in_action.Employee
import kotlin_in_action.employees
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock
import kotlin.io.path.Path
import kotlin.io.path.useLines

// 10.2.5 — Using inlined lambdas for resource management with withLock, use and useLines

/*
    Resource management (керування ресурсами) — процес отримання ресурсу перед виконанням операції,
    його використання та гарантованого звільнення після завершення операції, навіть якщо виник виняток.
    У Kotlin для цього використовуються inline-функції:
    - withLock — extension-функція для інтерфейсу Lock, яка захоплює блокування перед виконанням лямбди та звільняє
     його після завершення. Використовується для потокобезпечного доступу до спільних ресурсів.
    - use — extension-функція для ресурсів, які підтримують закриття (Closeable, AutoCloseable).
     Автоматично викликає close() після виконання лямбди. Аналог try-with-resources у Java.
    - useLines — дозволяє ліниво опрацьовувати рядки файлу через Sequence<String> без завантаження всього файлу в пам'ять.
     Автоматично закриває ресурс читання після завершення лямбди.
*/

// Завдання 1: Реалізувати звичайний та потокобезпечний банківські рахунки.
// Використати ReentrantLock і withLock для синхронізації доступу до балансу. Порівняти результати виконання
// операцій у декількох потоках та продемонструвати виникнення race condition.
class BankAccount(
    private var balance: Int
){
    fun deposit(amount: Int) {
        balance += amount
    }

    fun withdraw(amount: Int) {
            balance -= amount
    }

    fun getBalance(): Int = balance
}

class ThreadSafeBankAccount(
    private var balance: Int
){
    private val lock: Lock = ReentrantLock()

    fun deposit(amount: Int) {
        lock.withLock {
            balance += amount
        }
    }

    fun withdraw(amount: Int) {
        lock.withLock{
            balance -= amount
        }
    }

    fun getBalance(): Int = lock.withLock {
        balance
    }
}

// Завдання 2: Реалізувати експорт списку працівників у CSV-файл (employees.cvs).
// Використати BufferedWriter та inline-функцію use для автоматичного закриття ресурсу після запису.
fun exportEmployees(employees: List<Employee>, filePath: String){
    BufferedWriter(FileWriter(filePath)).use { writer ->
        val content ="name, age, salary, department" + employees.joinToString(separator = "\n", prefix ="\n") {
            "${it.name}, ${it.age}, ${it.salary}, ${it.department}"
        }
        writer.write(content)
    }
}

// Завдання 3: Реалізувати аналіз файлу логування за допомогою useLines (application.log).
// Опрацювати рядки файлу як Sequence та підрахувати кількість повідомлень кожного рівня.
fun analyzeLogs(filePath: String): Map<String, Int> {
    return Path(filePath).useLines { lines ->
        lines
            .filter { it.isNotBlank() }
            .groupingBy { it.substringBefore(" ").uppercase() }
            .eachCount()
    }
}

fun main() {
    println("Task 1")
    val threadSafeBankAccount = ThreadSafeBankAccount(0)
    val bankAccount = BankAccount(0)

    val threads1 = List(10) {
        thread {
            repeat(100_000) {
                bankAccount.deposit(100)
            }
        }
    }
    threads1.forEach { it.join() }

    println("Result without withLock")
    println("Expected balance: 100000000")
    println("Actual balance: ${bankAccount.getBalance()}")

    val threads2 = List(10) {
        thread {
            repeat(100_000) {
                threadSafeBankAccount.deposit(100)
            }
        }
    }
    threads2.forEach { it.join() }

    println("\nResult using withLock")
    println("Expected balance: 100000000")
    println("Actual balance: ${threadSafeBankAccount.getBalance()}")


    println("\nTask 2")
    val filePath = "src/kotlin_in_action/section_10_higher_order_functions/inline_functions/employees.csv"
    exportEmployees(employees, filePath)

    val file = File(filePath)

    println("File exists: ${file.exists()}")
    println("File path: ${file.absolutePath}")
    println("File content:")
    println(file.readText())


    println("\nTask 3")
    println(analyzeLogs("src/kotlin_in_action/section_10_higher_order_functions/inline_functions/application.log"))

}