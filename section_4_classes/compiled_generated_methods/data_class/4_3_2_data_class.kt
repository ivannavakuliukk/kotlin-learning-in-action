package kotlin_in_action.section_4_classes.compiled_generated_methods.data_class

// 4.3.2 - Data classes: Auto-generated implementations of universal methods

/*
    data class — спеціальний клас Kotlin, призначений переважно для зберігання даних. Компілятор автоматично генерує методи:
    - toString, equals, hashCode
    - copy() — створює новий об'єкт, дозволяючи змінити окремі властивості. Копіювання поверхневе (shallow copy).
    - componentN() — дозволяє використовувати деструктуризацію об'єктів.

    Особливості data class:
    - Primary constructor повинен мати хоча б один параметр val або var.
    - У генерації методів беруть участь лише властивості primary constructor.
    - Властивості, оголошені в тілі класу, не враховуються в автоматичних equals(), hashCode(), toString() та copy().
    - data class не може бути open, abstract, sealed або inner.

    @JvmRecord — анотація, яка дозволяє компілювати Kotlin data class у Java record для сумісності з Java.
    Основні обмеження:
    - Усі параметри primary constructor повинні бути val.
    - Заборонені додаткові властивості з backing field.
    - Не можна успадковувати інші класи, але можна реалізовувати інтерфейси.
 */

// Завдання 1: Створити data class Ticket і перевірити автоматичні equals(), hashCode(), toString().
// Перевірити унікальність квитків у HashSet
data class Ticket(
    val eventName: String,
    val seatNumber: Int,
    val price: Int
)

// Завдання 2: Створити data class UserSettings і змінювати окремі налаштування через copy().
// Перевірити незмінність оригіналу та різницю між == і === для точної копії.
data class UserSettings(
    val userName: String,
    val theme: String,
    val notificationsEnabled: Boolean
)

// Завдання 3: Створити @JvmRecord data class Parcel з обчислюваною властивістю та методом.
// Перевірити copy(), обмеження @JvmRecord і сумісність із Java record.
@JvmRecord
data class Parcel(
    val trackingNumber: Int,
    val destination: String,
    val weight: Double
){
    val isHeavy: Boolean
    get() = weight > 10.0

    fun deliveryInfo(): String = "Parcel number $trackingNumber is going to $destination"
}

fun main(){
    println("Task 1")
    val ticket1 = Ticket("Christmas concert", 2, 1000)
    val ticket2 = Ticket("Christmas concert", 2, 1000)
    val ticket3 = Ticket("Christmas concert", 3, 1000)

    println(ticket1 == ticket2)
    println(ticket3)

    val tickets = hashSetOf(ticket1, ticket2, ticket3)
    println(tickets.size)


    println("\nTask 2")
    val userSettings1 = UserSettings(
        userName = "User",
        theme = "Light",
        notificationsEnabled = true
    )
    val userSettings2 = userSettings1.copy(
        theme = "Dark",
    )
    val userSettings3 = userSettings1.copy(
        notificationsEnabled = false
    )
    val userSettingsCopy = userSettings1.copy()

    println("$userSettings1, $userSettings2, $userSettings3")

    println(userSettingsCopy == userSettings1)
    println(userSettingsCopy === userSettings1)


    println("\nTask 3")
    val parcel1 = Parcel(1, "Madrid", 10.0)
    val parcel2 = Parcel(1, "Madrid", 10.0)
    println(parcel1 == parcel2)
    val parcel3 = parcel1.copy(destination = "Laos")
}
