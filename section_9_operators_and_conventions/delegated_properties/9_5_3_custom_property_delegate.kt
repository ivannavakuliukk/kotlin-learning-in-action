package kotlin_in_action.section_9_operators_and_conventions.delegated_properties

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

// 9.5.3 - Implementing your own delegated property

/*
    1) Delegated property (делегована властивість) — властивість, яка передає логіку читання та запису свого значення іншому об'єкту — делегату.
    Для делегування використовується ключове слово by. Компілятор перенаправляє виклики гетера та сетера властивості до відповідних операторних функцій делегата.
    2) Щоб об'єкт можна було використовувати як делегат, він повинен надавати такі операторні функції:
        - getValue(thisRef: Any?, property: KProperty<*>) — обов'язкова для val та var.
        - setValue(thisRef: Any?, property: KProperty<*>, value: T) — обов'язкова лише для var.
        - provideDelegate(thisRef: Any?, property: KProperty<*>) — необов'язкова. Викликається під час прив'язування делегата до властивості,
      дозволяє виконати валідацію, налаштування і тд.
    3) Параметри операторних функцій:
        - thisRef — об'єкт, якому належить делегована властивість.
        - property: KProperty<*> — об'єкт, що містить метадані властивості, зокрема її назву.
        - value — нове значення, яке присвоюється властивості.
    4) Пакет kotlin.properties містить готові інтерфейси та класи для реалізації делегатів.
    Зокрема, ObservableProperty<T> — абстрактний клас, який зберігає значення, реалізує getValue() та setValue() і надає два методи для перевизначення:
        - beforeChange() — дозволяє прийняти або відхилити нове значення.
        - afterChange() — дозволяє реагувати на успішне присвоєння значення.

    Додатково*: Observer — поведінковий патерн проєктування, який встановлює залежність «один до багатьох» між об'єктами.
    При зміні стану одного об'єкта всі підписані на нього спостерігачі автоматично отримують сповіщення.

*/

// Завдання 1: Реалізувати універсальний делегат LoggingDelegate<T>
// для логування змін властивостей та валідації початкового значення
// за допомогою getValue(), setValue() та provideDelegate().
class LoggingDelegate<T>(private var initialValue: T){
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = initialValue
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val oldValue = initialValue
        this.initialValue = value
        println("${property.name} changed: $oldValue -> $value")
    }
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): LoggingDelegate<T> {
        val value = initialValue
        if(value is Double){
            require(value >= 0) {
                "${property.name} cannot be negative"
            }
        }
        println("Log: Delegate bound ${thisRef?.javaClass?.simpleName}.${property.name}, initialValue = $value")
        return this
    }
}

class BankAccount(
     owner: String,
     balance: Double,
){
    var owner by LoggingDelegate(owner)
    var balance by LoggingDelegate(balance)
}


// Завдання 2: Реалізувати патерн Observer для відстеження змін
// властивостей банківського рахунку, використовуючи власний
// делегат на основі ObservableProperty<T>.
fun interface Observer {
    fun onChange(name: String, oldValue: Any?, newValue: Any?)
}

open class Observable{
    private val observers = mutableListOf<Observer>()
    fun notifyObservers(name: String, oldValue: Any?, newValue: Any?) {
        observers.forEach {
            it.onChange(name, oldValue, newValue)
        }
    }

    fun subscribe(observer: Observer) {
        observers.add(observer)
    }

    fun unsubscribe(observer: Observer) {
        observers.remove(observer)
    }
}

class CustomObservableProperty<T>(
    initialValue: T,
    private val observable: Observable,
):ObservableProperty<T>(initialValue = initialValue){
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        if (oldValue != newValue) {
            observable.notifyObservers(property.name, oldValue, newValue)
        }
    }
}

class ObservableBankAccount(
    owner: String,
    balance: Double
): Observable(){
    var owner by CustomObservableProperty(owner, this)
    var balance by CustomObservableProperty(balance, this)
}

fun main() {
    println("Task 1")
    val bankAccount = BankAccount("Ivanna", 1500.0)
    bankAccount.owner = "Michael"
    bankAccount.balance = 2000.0

    println("\nTask 2")
    val loggingObserver = Observer { name, oldValue, newValue -> println("Log: property $name changed: $oldValue -> $newValue")}
    val lowBalanceAlertObserver = Observer { name, _, newValue ->
        if (name == "balance" && newValue is Double && newValue < 500) {
            println("Warning: low balance $newValue$")
        }
    }

    val observableBankAccount = ObservableBankAccount("Michael", 1500.0)
    observableBankAccount.subscribe(loggingObserver)
    observableBankAccount.subscribe(lowBalanceAlertObserver)

    // Перевірка спостерігача логування
    observableBankAccount.balance = 2000.0
    observableBankAccount.owner = "Ivanna"

    // Перевірка спостерігача з попередженням
    observableBankAccount.balance = 400.0

    // Перевірка зміни на ті самі значення
    observableBankAccount.owner = "Ivanna"
    observableBankAccount.balance = 400.0
}