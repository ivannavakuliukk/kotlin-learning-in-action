package kotlin_in_action.section_9_operators_and_conventions

// Завдання 2 з 9.1.1
data class Money(val amount: Int){
    operator fun plus(other: Money): Money = Money(amount + other.amount)
    operator fun minus(other: Money): Money = Money(amount - other.amount)
    operator fun times(other: Int): Money = Money(amount * other)
    operator fun div(other: Int): Money = Money(amount / other)
    operator fun rem(other: Int): Money = Money(amount % other)
}