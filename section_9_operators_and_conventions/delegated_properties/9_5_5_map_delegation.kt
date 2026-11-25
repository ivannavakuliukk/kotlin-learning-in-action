package kotlin_in_action.section_9_operators_and_conventions.delegated_properties

// 9.5.5 - Accessing dynamic attributes by delegating to maps

/*
    Kotlin дозволяє використовувати Map та MutableMap як делегати властивостей завдяки стандартним операторним
    extension-функціям getValue() та setValue(). При зверненні до делегованої властивості ці функції отримують її
    назву через KProperty.name та використовують її як ключ для читання або запису значення в мапі.
    - Map підтримує делегування властивостей val, а MutableMap — val та var.
    - Значення властивостей зберігаються безпосередньо в мапі, а не у власних backing fields.
    - Зміна значення в мапі автоматично відображається при читанні властивості.
    Такий механізм дозволяє реалізувати expando object — об'єкт, набір атрибутів якого можна динамічно розширювати
    під час виконання програми (runtime).
*/

// Завдання 1: Реалізувати читання атрибутів профілю
// користувача через делегування властивостей до Map.
class UserProfile(data: Map<String, Any>){
    val name: String by data
    val age: Int by data
    val email: String by data
    val isActive: Boolean by data
}

// Завдання 2: Реалізувати динамічні налаштування застосунку
// через делегування властивостей до MutableMap.
class AppSettings{
    private val settings = mutableMapOf<String, Any>(
        "theme" to "light",
        "language" to "en",
        "notificationsEnabled" to true
    )

    var theme: String by settings
    // те що створюється під капотом
//    var theme: String
//        get() = settings.getValue(this, this::theme)
//        set(value) = settings.setValue(this, this::theme, value)
    var language: String by settings
    var notificationsEnabled: Boolean by settings

    fun setAttribute(name: String, value: Any){
        settings[name] = value
    }

    fun getAttribute(name: String) = settings[name]

    fun getAllSettings() = settings.toMap()
}

fun main() {
    println("Task 1")
    val userData = mapOf(
        "name" to "Michael",
        "age" to 25,
        "email" to "michael@gmail.com",
        "isActive" to true,
        "gender" to "Female"
    )
    val profile = UserProfile(userData)

    println("Name: ${profile.name}, age: ${profile.age}, email: ${profile.email}, isActive: ${profile.isActive}")


    println("\nTask 2")
    val appSettings = AppSettings()
    appSettings.theme = "dark"
    println(appSettings.getAllSettings())

    appSettings.setAttribute("geolocation", true)
    println(appSettings.getAllSettings())
}