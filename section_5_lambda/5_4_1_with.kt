package kotlin_in_action.section_5_lambda

// 5.4.1 lambda with receivers - with

/*
    with - працюємо з об'єктом і отримуємо результат виконання блоку.
    Всередині об'єкт доступний через this.
    Зазвичай використовується: виконати кілька операцій з одним об'єктом
    без повторення посилання на цей о'єкт.
 */

data class User(
    val name: String,
    val age: Int,
    val city: String
)

fun main(){
    val user = User("Anna", 22, "Lutsk")
    val description = with(user){
        "$name, $age years old, from $city"
    }
    println(description)
}