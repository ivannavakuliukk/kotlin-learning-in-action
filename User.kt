package kotlin_in_action

data class User(
    val name: String,
    val age: Int?,
    val city: String?
)

val users = listOf(
    User("Anna", 22, "Lutsk"),
    User("Oleh", null, "Lviv"),
    User("Maria", 25, null),
    User("Ivan", null, null)
)