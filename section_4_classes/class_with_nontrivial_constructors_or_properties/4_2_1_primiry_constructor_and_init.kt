package kotlin_in_action.section_4_classes.class_with_nontrivial_constructors_or_properties

// 4.2.1 - Initializing classes: Primary constructor and initializer block

/*
    - val / var — перетворюють параметри конструктора на властивості класу. Без них параметри не стають властивостями.
    - init — блок ініціалізації, який виконується під час створення об'єкта. Використовується для перевірки даних і додаткової логіки ініціалізації.
    - Значення за замовчуванням — дозволяють не передавати всі аргументи під час створення об'єкта.
    - Успадкування — дочірній клас передає необхідні аргументи конструктору батьківського класу. Спочатку ініціалізується батьківський клас, потім дочірні
 */

// Завдання 1: Створити UserProfile із primary constructor, значенням country за замовчуванням
// та перевіркою username і age використовуючи require().
// Розглянути, як Kotlin спрощує ініціалізацію властивостей класу. (primary constructor 3 способами)

/*
     Повна форма: primary constructor приймає параметри, але самі параметри не стають властивостями класу.
     Тому властивості потрібно оголосити окремо та присвоїти їм значення в блоці init.
     Блок init виконується під час створення об'єкта й дозволяє ініціалізувати властивості та перевіряти дані.
 */
class UserProfileFull constructor (
    _userName: String,
    _age: Int,
    _country: String = "Ukraine"
){
    val userName: String
    val age: Int
    val country: String

    init {
        require(_age >= 0) { "Age must be non-negative" }
        require(_userName.isNotEmpty()) { "UserName must not be empty" }
        userName = _userName
        age = _age
        country = _country
    }
}

/*
     Скорочена форма: У Kotlin властивості можна ініціалізувати безпосередньо під час оголошення.
     Тому присвоєння з init переносяться до оголошень властивостей, а в init залишаються лише перевірки.
     constructor можна опустити, якщо primary constructor не має анотацій або модифікатора видимості.
 */
class UserProfileShort (
    _userName: String,
    _age: Int,
    _country: String = "Ukraine"
){
    val userName: String = _userName
    val age: Int = _age
    val country: String = _country

    init {
        require(_age >= 0) { "Age must be non-negative" }
        require(_userName.isNotEmpty()) { "UserName must not be empty" }
    }

}

/*
     Найбільш спрощена форма: додавання val до параметрів primary constructor автоматично оголошує
     та ініціалізує відповідні властивості класу. Блок init залишається для додаткової логіки.
 */
class UserProfile(
    val userName: String,
    val age: Int,
    val country: String = "Ukraine"
){
    init {
        require(age >= 0) { "Age must be non-negative" }
        require(userName.isNotEmpty()) { "UserName must not be empty" }
    }
    fun showProfile(){
        println(
            "User: $userName, Age: $age, Country: $country"
        )
    }
}


// Завдання 2: Створити ApiKey із приватним конструктором, заборонивши пряме створення об'єктів.
// У companion object реалізувати create() з перевіркою ключа, а в init вивести повідомлення.
// Приклад фабричного методу
class ApiKey private constructor(val value: String) {
    companion object {
        fun create(value: String): ApiKey {
            require(value.length >= 8){"The value must contain at least 8 characters"}
            return ApiKey(value)
        }
        init {
            println("API key initialized")
        }
    }
}


// Завдання 3: Створити Delivery та дочірній ExpressDelivery, передаючи параметри в конструктор батьківського класу.
// Використати init для перевірки даних і дослідити порядок ініціалізації класів.
open class Delivery(
    val trackingNumber: String,
    val destination: String
) {
    init{
        require(trackingNumber.isNotEmpty()) { "TrackingNumber must not be empty" }
        println("Delivery initialized")
    }
    open fun showDetails(){
        println("Delivery: $destination $trackingNumber")
    }
}

class ExpressDelivery(
    val priority: Int = 1, trackingNumber: String, destination: String
) : Delivery(trackingNumber, destination) {

    init{
        require(priority in 0 .. 5){ "Priority must be between 0 and 5" }
        println("Express delivery initialized")
    }

    override fun showDetails() {
        println("Express Delivery: $destination $trackingNumber, $priority priority")
    }

}

fun main(){
    println("Task 1")
//    val profile1 = UserProfile(userName = "John", age = -1)
//    profile1.showProfile()
    val profile2 = UserProfile("Mary", 30, country = "USA")
    profile2.showProfile()

    println("\nTask 2")
    val key = ApiKey.create("secret123")
//    val apiKey = ApiKey("secret123")

    println("\nTask 3")
    val delivery = ExpressDelivery(trackingNumber = "345", destination = "USA")
    val delivery2 = ExpressDelivery(trackingNumber = "345", destination = "USA", priority = 2)
}