package kotlin_in_action.section_4_classes.object_keyword

// 4.4.2 - Companion objects: A place for factory method and static members

/*
    companion object — спеціальний об'єкт усередині класу, який дозволяє оголошувати методи та властивості,
    доступні через назву класу без створення його екземпляра.
    - Використовується для спільних властивостей, констант і фабричних методів.
    - Має доступ до всіх private членів зовнішнього класу, але для доступу до властивостей екземпляра потрібен сам екземпляр.
    - На відміну від Java static, є повноцінним об'єктом Kotlin.
    Фабричний метод (Factory Method) — метод, який створює та повертає об'єкт, приховуючи або контролюючи логіку його створення.
    Може перевіряти параметри, повертати готові екземпляри або вибирати потрібну реалізацію.
*/

// Завдання 1: Створити клас Account із приватним конструктором і фабричними методами в companion object.
// Реалізувати створення акаунтів через Email та Google і перевірити їхні дані.
class Account private constructor(
    val username: String,
    val email: String,
    val provider: String,
){
    companion object {
        fun fromEmail(username: String, email: String): Account {
            return Account(username, email, "Email")
        }

        fun fromGoogle(username: String, email: String): Account {
            return Account(username, email, "Google")
        }
    }

    override fun toString() = "Account created by $provider: $username, $email"
}

// Завдання 2: Створити клас PurchaseOrder із приватним конструктором і спільним лічильником замовлень.
// Реалізувати фабричний метод create() з перевіркою кількості товарів та підрахунком створених замовлень.
class PurchaseOrder private constructor(
    val productName: String,
    val quantity: Int
){

    companion object {
        private var totalOrders: Int = 0

        fun getTotalOrders() = totalOrders

        fun create(productName: String, quantity: Int): PurchaseOrder {
            require(quantity > 0) { "Quantity must be positive" }
            totalOrders ++
            return PurchaseOrder(productName, quantity)
        }
    }
}

fun main() {
    println("Task 1")
    val account1 = Account.fromGoogle("User1", "user1@gmail.com")
    val account2 = Account.fromEmail("User2", "user2@ukr.net")
    println(account1)
    println(account2)

    println("\nTask 2")
    val order1 = PurchaseOrder.create("Foundation", 2)
    val order2 = PurchaseOrder.create("Powder", 1)
    val order3 = PurchaseOrder.create("Lip oil", 3)
    println(PurchaseOrder.getTotalOrders())
}