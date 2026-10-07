package kotlin_in_action.section_5_lambda

// 5.4.3 - lambda with receivers - also

/*
    also - виконуємо додаткову дію з об'єктом і повертаємо цей самий об'єкт.

    Всередині об'єкт доступний через it.

    Зазвичай: логування, перевірка або виконання додаткової дії
    з об'єктом, не змінюючи основний ланцюжок викликів.
*/

fun main(){
    val numbers = mutableListOf(1, 2, 3)
        .also {
            it.add(4)
        }
    println("Numbers: $numbers")
}