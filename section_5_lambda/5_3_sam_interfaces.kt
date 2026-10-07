package kotlin_in_action.section_5_lambda

// 5.3 - defining sam interfaces in kotlin - fun interfaces

/*
    fun interface - функціональний інтерфейс, який має рівно одну
    абстрактну функцію.

    Використовується, коли потрібно створити власний функціональний
    інтерфейс і передавати його реалізацію у вигляді лямбди.

    PasswordValidator
    → наш власний функціональний інтерфейс для перевірки пароля.

    SAM (Single Abstract Method)
    → інтерфейс з однією абстрактною функцією.
*/

fun interface PasswordValidator{
    fun validate(password: String): Boolean
}

fun checkPassword(
    password: String,
    validator: PasswordValidator
):Boolean{
    return validator.validate(password)
}

fun main(){
    val password = "Kotlin123"
    val result = checkPassword(password){it.length > 8}

    println(result)
}