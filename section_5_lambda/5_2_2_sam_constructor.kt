package kotlin_in_action.section_5_lambda

// 5.2.2 - SAM constructors: explicit conversion of lambdas to functional interfaces

/*
    SAM constructor - дозволяє явно перетворити лямбду
    на об'єкт функціонального інтерфейсу (fun interface).

    Використовується, коли потрібно створити об'єкт
    функціонального інтерфейсу на основі лямбди.

    Validator { text -> text.length > 3 }
    → створюємо об'єкт Validator з лямбди.

    SAM (Single Abstract Method) - функціональний інтерфейс,
    який має одну абстрактну функцію.
*/

fun interface Validator {
    fun validate(text: String): Boolean
}

fun checkText(
    text: String,
    validator: Validator
): Boolean {
    return validator.validate(text)
}

fun main() {
    val text = "Kotlin"

    // sam constructor
    val validator = Validator { text ->
        text.length > 3
    }

    val result = checkText(text, validator)
    // Або Kotlin автоматично перетворить лямбду на Validator:
    // val result = checkText(text) { it.length > 3 }

    println(result)
}