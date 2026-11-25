package kotlin_in_action.section_9_operators_and_conventions.delegated_properties

// 9.5.4 - Delegated properties are translated to hidden properties with custom accessor (як працює під капотом)

/*
    Компілятор перетворює делеговану властивість на приховану властивість (property$delegate) та власні аксесори:
    - provideDelegate() — викликається під час ініціалізації, якщо визначений.
    - getValue() — викликається в гетері.
    - setValue() — викликається в сетері.
    Ключове слово by автоматизує цей механізм, усуваючи необхідність реалізовувати його вручну.
*/

// Завдання 1 (використовуємо LoggingDelegate з 9.5.3):
// Реалізувати механізм делегування властивостей
// вручну без by, імітуючи код, який генерує компілятор.
class BankAccountManual(owner: String, balance: Double){
    private val ownerDelegate = LoggingDelegate(owner).provideDelegate(this, this::owner)
    private val balanceDelegate = LoggingDelegate(balance).provideDelegate(this, this::balance)

    var owner: String
        get() = ownerDelegate.getValue(this, this::owner)
        set(value) = ownerDelegate.setValue(this, this::owner, value)

    var balance: Double
        get() = balanceDelegate.getValue(this, this::balance)
        set(value) = balanceDelegate.setValue(this, this::balance, value)
}

fun main() {
    println("Task 1")
    val bankAccount = BankAccountManual("Ivanna", 1500.0)
    bankAccount.owner = "Michael"
    bankAccount.balance = 2000.0
}