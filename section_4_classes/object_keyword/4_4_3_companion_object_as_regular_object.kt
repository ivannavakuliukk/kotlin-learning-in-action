package kotlin_in_action.section_4_classes.object_keyword

// 4.4.3 - Companion objects as regular objects

/*
    companion object — повноцінний об'єкт Kotlin:
    - Реалізація інтерфейсів: companion object може реалізовувати інтерфейс і використовуватися як об'єкт цього типу(під іменем зовн.класу).
    - Функції розширення: дозволяють додавати нові функції до companion object без зміни самого класу.
    - Сумісність із Java: звичайні методи доступні через Companion, @JvmStatic дозволяє викликати їх через назву класу, а @JvmField відкриває властивість як поле.

    Додатково — const val: константа, значення якої відоме під час компіляції; на відміну від звичайного val, вона доступна з Java безпосередньо як статичне поле.
*/

// Завдання 1: Створити клас Customer із companion object, який реалізує EntityFactory<Customer>.
// Створити об'єкти через фабричний метод і передати companion object у змінну типу інтерфейсу.
interface EntityFactory<T>{
    fun create(id: Int): T
}

class Customer private constructor(
    val id: Int,
    val name: String
){
    companion object: EntityFactory<Customer>{
        override fun create(id: Int): Customer {
            require(id > 0) { "Customer ID must be greater than zero." }
            return Customer(id, "Customer_$id")
        }

    }

    override fun toString(): String {
       return "Customer(id=$id, name='$name')"
    }
}

// Завдання 2: Створити клас Temperature із фабричним методом fromCelsius() у companion object.
// Додати функцію розширення fromFahrenheit() для перетворення температури та створення об'єкта.
class Temperature private constructor(
    val celsius: Double
){
    companion object{
        fun fromCelsius(celsius: Double): Temperature = Temperature(celsius)
    }

    override fun toString(): String = "Celsius($celsius)"
}

fun Temperature.Companion.fromFahrenheit(fahrenheit: Double): Temperature {
    val celsius = (fahrenheit - 32) * 5 / 9
    return Temperature.fromCelsius(celsius)
}

// Завдання 3: Створити AppSettings із const val, @JvmStatic та фабричними методами в companion object.
// Перевірити доступ із Java до константи, статичного методу та звичайного методу через Companion.
class AppSettings private constructor(
    val environment: String
){
    companion object{
        const val ENVIRONMENT = "Production"

        @JvmStatic
        fun createDefault(): AppSettings = AppSettings(ENVIRONMENT)
        fun createDevelopment(): AppSettings = AppSettings("Development")
    }
}

fun main() {
    println("Task 1")
    val customer = Customer.Companion.create(1)
    val factory: EntityFactory<Customer> = Customer
    val customer2 = factory.create(2)
    println("$customer and $customer2")

    println("\nTask 2")
    val temperature1 = Temperature.fromCelsius(40.1)
    val temperature2 = Temperature.fromFahrenheit(98.6)
    println("$temperature1 and $temperature2")
}