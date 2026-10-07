package kotlin_in_action

data class Employee(
    val name: String,
    val age: Int,
    val salary: Int,
    val department: String,
)
val employees = listOf(
    Employee("Anna", 25, 55000, "IT"),
    Employee("Oleg", 31, 45000, "Sales"),
    Employee("Sofia", 28, 70000, "IT"),
    Employee("Max", 24, 38000, "Marketing"),
    Employee("Olivia", 35, 62000, "HR"),
    Employee("Daniel", 29, 48000, "IT")
)