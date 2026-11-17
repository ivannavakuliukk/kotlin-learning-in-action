package kotlin_in_action.section_4_classes.class_with_nontrivial_constructors_or_properties

// 4.2.2 - Secondary constructors: Initializing the superclass in different ways

/*
    Secondary constructors — додаткові конструктори класу, які дозволяють створювати об'єкти різними способами.
    Оголошуються через constructor.
    - this() — делегує виклик іншому конструктору поточного класу.
    - super() — викликає конструктор батьківського класу.
    - Якщо є primary constructor, усі secondary constructors повинні прямо або опосередковано делегувати йому через this().
    - Якщо немає primary constructor, secondary constructors можуть безпосередньо викликати super() для ініціалізації
      батьківського класу.
 */

// Завдання 1: Створити Notification без primary constructor із двома secondary constructors,
// які по-різному ініціалізують батьківський клас Message через super().
open class Message(
    val text: String,
    val sender: String
)

class Notification: Message{
    constructor (text: String, sender: String) : super(text, sender)

    constructor(text: String) : super(text, "System")

    fun showNotification(){
        println("$sender: $text")
    }
}

// Завдання 2: Створити Subscription із primary constructor та двома secondary constructors,
// які делегують виклики через this(). Використати init для перевірки тривалості підписки.
class Subscription(
    val plan: String,
    val months: Int
){
    constructor(plan: String) : this(plan, 1)

    constructor(): this("Free")

    init {
        require(months > 0) { "Months must be greater than 0" }
        println("Subscription initialized")
    }

    fun showSubscription(){
        println("Subscription plan: $plan for $months months")
    }

}

fun main(){
    println("Task 1")
    val notification1 = Notification("Update available")
    val notification2 = Notification("Hello!", "Admin")

    notification1.showNotification()
    notification2.showNotification()


    println("\nTask 2")
    val subscription1 = Subscription("Premium", 12)
    val subscription2 = Subscription("Standard")
    val subscription3 = Subscription()

    subscription1.showSubscription()
    subscription2.showSubscription()
    subscription3.showSubscription()
}