package kotlin_in_action.section_4_classes.defining_class_hierarchies

// 4.1.5 - Sealed classes: Defining restricted class hierarchies

/*
    sealed — обмежує ієрархію типів: прямі нащадки мають бути оголошені в тому самому пакеті й модулі.
    Завдяки цьому компілятор знає всі можливі типи й дозволяє використовувати when без else.
    - sealed class — використовується, коли нащадки мають спільну основу. Може мати конструктор, зберігати стан і містити реалізовані методи.
    - sealed interface — використовується, коли різні класи повинні реалізовувати спільний контракт.
 */

// Завдання 1: Створити sealed class TransactionResult із трьома результатами переказу.
// Реалізувати transfer() для перевірки суми та балансу й обробити всі результати через when без else.
sealed class TransactionResult(val message: String)
class Success(val amount: Int) :
    TransactionResult("Transfer successful")

class InsufficientFunds(val balance: Int) :
    TransactionResult("Insufficient funds")

class InvalidAmount(val amount: Int) :
    TransactionResult("Invalid transfer amount")

fun transfer(balance: Int, amount: Int): TransactionResult =
    when{
        amount <= 0 -> InvalidAmount(amount)
        amount > balance -> InsufficientFunds(balance)
        else -> Success(amount)
    }


// Завдання 2: Створити sealed interface SmartHomeEvent із трьома типами подій розумного будинку.
// Реалізувати handleEvent() для обробки подій через when та перевірити роботу на списку подій.
sealed interface SmartHomeEvent

class LightOn(val room: String): SmartHomeEvent
class TemperatureChange(val temperature: Int): SmartHomeEvent
object DoorLocked: SmartHomeEvent

fun handleEvent(event: SmartHomeEvent){
    when(event){
        is LightOn -> println("Light is on in ${event.room}")
        DoorLocked -> println("Entrance door locked")
        is TemperatureChange -> println("Temperature changed for ${event.temperature}")
    }
}

fun main(){
    println("Task 1")
    when (val transactionResult = transfer(100,100)) {
        is Success -> println("${transactionResult.message}: ${transactionResult.amount}")
        is InsufficientFunds -> println("${transactionResult.message}: ${transactionResult.balance}")
        is InvalidAmount -> println("${transactionResult.message}: ${transactionResult.amount}")
    }

    println("\nTask 2")
    val eventList: List<SmartHomeEvent> = listOf(
        LightOn("bathroom"), TemperatureChange(33), DoorLocked,
    )
    eventList.forEach { handleEvent(it) }
}