package kotlin_in_action.section_4_classes.defining_class_hierarchies

// 4.1.1 - Interfaces in Kotlin
/*
    Інтерфейси в Kotlin визначають контракт, який класи повинні реалізувати.
    Вони можуть містити абстрактні методи та методи з реалізацією за замовчуванням.
    - Клас може реалізовувати кілька інтерфейсів.
    - Для реалізації або перевизначення методів використовується override.
    - Якщо кілька інтерфейсів мають однаковий метод із реалізацією, клас повинен явно розв'язати конфлікт.
    - Через super<Interface>.method() можна викликати реалізацію конкретного інтерфейсу.

    Додатково: різниця від абстрактного класу
    Абстрактний клас — спільна основа для споріднених класів. Може мати конструктор, зберігати стан (властивості з полями)
    та містити методи з реалізацією і без неї. Клас може успадкувати лише один клас.
    Інтерфейс — визначає спільну поведінку (контракт). Може містити методи з реалізацією і без неї, але не має конструктора
    та не може зберігати стан у власних полях. Клас може реалізувати кілька інтерфейсів.
 */

// Завдання: Створити інтерфейси Clickable і Focusable з методами для натискання та фокусування.
// Реалізувати їх у Button і розв'язати конфлікт методу showOff()
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}