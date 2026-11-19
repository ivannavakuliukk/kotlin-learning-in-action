package kotlin_in_action.section_4_classes.inline_classes

// 4.5 - Extra type safety without overhead: Inline classes

/*
    Inline Class (Value Class) — спеціальний клас Kotlin, який обгортає значення, створюючи окремий тип без необхідності
    створення додаткового об'єкта в пам'яті в багатьох випадках.
    (Наприклад функція приймає параметр певного типу(Int), але це можуть бути сематично різні аргументи (різні валюти, різні одиниці виміру)
    Оголошується через @JvmInline value class.
    - Типобезпека: дозволяє створювати різні типи для однакових базових значень (UserId та OrderId), запобігаючи їх випадковому змішуванню.
    - Unboxing: компілятор часто використовує безпосередньо базове значення (Int, String) без створення об'єкта-обгортки.
    - Boxing: об'єкт-обгортка потрібен у певних ситуаціях, наприклад при використанні nullable-типів, інтерфейсів або generics.
    - Можна оголошувати методи й обчислювані властивості, реалізовувати інтерфейси, мати лише одну val властивість в primary constructor.
    - Inline класи не беруть участь у класовій ієрархії
*/

// Завдання 1: Створити два value class (UserId та OrderId), які обгортають Int.
// Продемонструвати типобезпеку: неможливість передати один тип замість іншого.
@JvmInline
value class UserId(val id: Int)

@JvmInline
value class OrderId(val id: Int)

fun findUser(id: UserId){
    println("user with id $id found")
}

fun findOrder(order: OrderId){
    println("order with id ${order.id}")
}

// Завдання 2: Створити value class Email з валідацією через require(), методом і обчислюваною властивістю.
// Реалізувати інтерфейс Printable та обробити некоректні дані через try-catch.
interface Printable{
    fun printValue()
}

@JvmInline
value class Email(val value: String): Printable{
    val userName: String
        get() = value.substringBefore("@")

    init {
        require(
            value.contains("@")
                    && value[0] != '@'
                    && value.last() != '@'
        ) {"Invalid email format"}
    }

    fun getDomain(): String = value.substringAfter('@')

    override fun printValue() {
        println("Email: $value")
    }
}


fun main() {
    println("Task 1")
    val userId = UserId(1)
    val orderId = OrderId(1)
    findOrder(orderId)
    findUser(userId)
//    так буде помилка - хоть і Int, але не та обгортка
//    findOrder(userId)

    println("\nTask 2")
    val email = Email("ivanna@gmail.com")
    email.printValue()
    println(email.userName)
    println(email.getDomain())

    try {
        val incorrectEmail = Email("incorrect email")
        println(incorrectEmail.userName)
        println(incorrectEmail.getDomain())
    }catch (e: IllegalArgumentException){
        println(e.message)
    }
}