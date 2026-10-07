package kotlin_in_action.section_5_lambda

// 5.1.5 - member references

/*
    Member reference (::) - посилання на функцію або властивість класу
    без її виклику.

    Використовується, коли лямбда просто викликає одну конкретну
    функцію або звертається до однієї властивості об'єкта.

    Замість:
        users.map { it.name }

    можна написати:
        users.map(Person::name)
*/

data class Person(
    val name: String,
    val age: Int
)

fun main() {
    val users = listOf(
        Person("Anna", 22),
        Person("Oleg", 25),
        Person("Sofia", 19)
    )
    val names = users.map (Person::name)
    println(names)
}
