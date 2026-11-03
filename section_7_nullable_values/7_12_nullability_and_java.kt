package kotlin_in_action.section_7_nullable_values

// 7.12 - nullability and java

/*
    Platform type — Java-тип, nullability якого невідома Kotlin.
    Kotlin дозволяє працювати з ним як із nullable або non-nullable типом,
    тому неправильне припущення може призвести до NullPointerException.
    Анотації @Nullable і @NotNull допомагають Kotlin визначити nullability: @Nullable → T?, @NotNull → T
 */

// Завдання 1
// Створити Java-клас UserService з методами getUsername() та getCity().
// Позначити їх @NotNull та @Nullable і перевірити, як Kotlin визначає типи результатів.

// Завдання 2
// Створити Java-клас LegacyUserService без nullability-анотацій.
// Викликати getUserName() у Kotlin як platform type та перевірити поведінку
// при звичайному значенні й null.

fun main(){
    println("Task 1")
    val userService = UserService(
        "Maria", "Lviv"
    )
    val userName: String = userService.username
    println(userName)
    val userCity: String? = userService.city
    println(userCity ?: "Unknown city")

    println("Task 2")
    val legacyUserService = LegacyUserService(null)
//    println(legacyUserService.userName.length)
    val userNameLength: String? = legacyUserService.userName
    println(userNameLength?.length ?: "Unknown user name")

}