package kotlin_in_action.section_4_classes.object_keyword

// 4.4.1 - Object declarations: Singletons made easy

/*
    object — конструкція Kotlin, яка одночасно оголошує клас і створює його єдиний екземпляр, реалізуючи патерн Singleton
    без ручного керування створенням об'єкта.
    Особливості object:
    - Може містити властивості, методи та зберігати спільний стан.
    - Може успадковувати класи та реалізовувати інтерфейси.
    - Може бути оголошений усередині іншого класу як вкладений об'єкт.
    - Не має доступних для оголошення конструкторів.
    - Ініціалізується під час першого звернення до об'єкта.
    Сумісність із Java: до звичайного Kotlin object можна звернутися через статичне поле INSTANCE.
*/

// Завдання 1: Створити Singleton NotificationManager через object для надсилання сповіщень.
// Реалізувати спільний лічильник, отримання кількості повідомлень та скидання стану.
object NotificationManager{
    private var notificationCount: Int = 0

    fun send(message: String) {
        notificationCount++
        println("Message: $message")
    }

    fun getCount(): Int{
        return notificationCount
    }

    fun reset(){
        notificationCount = 0
    }
}


// Завдання 2: Створити data class CatalogItem із вкладеними object-компараторами.
// Реалізувати сортування товарів за зростанням ціни та спаданням рейтингу через Comparator.
data class CatalogItem(
    val name: String,
    val price: Int,
    val rating: Double
){
    // Comparator<T> — інтерфейс, який визначає правила порівняння двох об'єктів для їх сортування.
    // sortedWith(comparator) — сортує колекцію за заданим компаратором, повертаючи новий список.
    object ByPrice: Comparator<CatalogItem>{
        override fun compare(o1: CatalogItem, o2: CatalogItem): Int {
            return o1.price.compareTo(o2.price)
        }

    }

    object ByRating: Comparator<CatalogItem>{
        override fun compare(o1: CatalogItem, o2: CatalogItem): Int {
            return o2.rating.compareTo(o1.rating)
        }
    }
}


// Завдання 3: Створити Singleton AppEventHandler, який успадковує EventHandler.
// Перевизначити обробку подій, рахувати їх кількість і перевірити доступ із Java через INSTANCE (файл ObjectTest).
open class EventHandler{
    open fun handle(event: String){
        println("Handling event: $event")
    }
}

object AppEventHandler: EventHandler(){
    var eventCount = 0
    override fun handle(event: String){
        eventCount++
        super.handle(event)
    }
    fun getHandledCount(): Int = eventCount
}

fun main() {
    println("Task 1")
    NotificationManager.send("Hello")
    NotificationManager.send("World")
    println("Notification count: ${NotificationManager.getCount()}")
    NotificationManager.reset()
    println("Notification count: ${NotificationManager.getCount()}")

    println("\nTask 2")
    val catalogItems = listOf(
        CatalogItem("Lipstick", 500, 4.5),
        CatalogItem("Brush", 400, 4.3),
        CatalogItem("Powder", 200, 3.1),
        CatalogItem("Foundation", 300, 2.0)
    )

    println(catalogItems.sortedWith(CatalogItem.ByPrice))
    println(catalogItems.sortedWith(CatalogItem.ByRating))

    println("\nTask 3")
    AppEventHandler.handle("Showing splash screen")
    AppEventHandler.handle("Login successful")

}