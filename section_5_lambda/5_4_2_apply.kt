package kotlin_in_action.section_5_lambda

// lambda with receivers - apply

/*
    apply - виконуємо операції над об'єктом і повертаємо цей самий об'єкт (receiver)
    Всередині о'єкт доступний через this
    Зазвичай використовується: створення та налаштування об'єкта
 */

data class Profile(
    var name: String = "",
    var age: Int = 0,
    var city: String = ""
)

fun main(){
    val user = Profile().apply {
        name = "Anna"
        age = 22
        city = "Lutsk"
    }
    println(user)
}