package kotlin_in_action.section_5_lambda

// 5.1.6 - bound callable reference

/*
    Bound callable reference (::) - посилання на функцію або властивість,
    яке вже прив'язане до конкретного об'єкта.

    Тобто спочатку створюємо об'єкт, а потім звертаємося
    до його функції або властивості через ::.

    calculator::multiply
    → посилання на multiply саме об'єкта calculator.

    Зазвичай використовується, коли потрібно передати
    функцію конкретного об'єкта як аргумент.
*/

class Calculator(
    val multiplier: Int
) {
    fun multiply(number: Int): Int {
        return number * multiplier
    }
}

fun main(){
    val calculator = Calculator(3)
    val numbers = listOf(1, 2, 3, 4, 5)
    val result = numbers.map (calculator::multiply)
    println(result)
}