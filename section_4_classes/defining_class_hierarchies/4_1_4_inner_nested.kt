package kotlin_in_action.section_4_classes.defining_class_hierarchies

// 4.1.4 - Inner and nested classes: nested by default

/*
    У Kotlin класи можна оголошувати всередині інших класів. Є два види:
    - nested — вкладений клас за замовчуванням (без спеціального модифікатора). Не має доступу до екземпляра зовнішнього
     класу. Створюється через Outer.Nested().
    - inner — вкладений клас із модифікатором inner. Зберігає посилання на екземпляр зовнішнього класу та має доступ до
    його властивостей і методів. Створюється через outer.Inner().
 */

// Завдання 1: Створити клас AppConfig із вкладеним nested-класом DatabaseConfig.
// Передавати host і port через конструктор та виводити їх методом showConnection().
class AppConfig(val appName: String) {
    class DatabaseConfig(val host: String, val port: Int) {
        fun showConnection(){
            println("Database connection to $host:$port")
        }
    }
}

// Завдання 2: Створити клас BankAccount із балансом та inner-класом Transaction.
// Реалізувати поповнення і зняття коштів.
class BankAccount(val owner: String, var balance: Int) {
    inner class Transaction{
        fun deposit(amount: Int) {
            balance += amount
        }
        fun withdraw(amount: Int) {
            if (amount in 1..balance) {
                balance -= amount
            }
        }
    }
}


// Завдання 3: Створити клас OnlineStore із назвою магазину та лічильником замовлень.
// Nested-клас DeliveryOptions розраховує вартість доставки, а inner-клас Order
// оформлює замовлення, використовуючи назву магазину та збільшуючи лічильник.
class OnlineStore(
    val storeName: String,
    var totalOrders: Int = 0
){
    class DeliveryOptions{
        fun calculateCost(distance: Int): Int{
            return distance * 5
        }
    }

    inner class Order{
        fun placeOrder(productName:String){
            totalOrders += 1
            println("$storeName: $productName sold in $totalOrders order")
        }
    }
}


fun main(){
    println("Task 1")
    val database = AppConfig.DatabaseConfig("jdbc:h2:mem:test;MODE=MySQL", port = 5432)
    database.showConnection()

    println("\nTask 2")
    val account = BankAccount("Mary Lee", 1000)
    val transaction = account.Transaction()
    transaction.deposit(1000)
    println(account.balance)
    transaction.withdraw(500)
    println(account.balance)

    println("\nTask 3")
    val delivery = OnlineStore.DeliveryOptions()
    println(delivery.calculateCost(10))

    val store = OnlineStore("Rozetka")
    val order = store.Order()
    order.placeOrder("Laptop")
}