package kotlin_in_action.section_6_collections_and_sequences

// 6.1.1 - Removing and transforming elements - filter and map

/*
    filter - відфільтровує елементи колекції за умовою.
    Повертає нову колекцію тільки з елементами,
    які відповідають умові.
    map - перетворює кожен елемент колекції
    та повертає нову колекцію з результатами перетворення.

    mapIndexed, filterIndexed - те саме тільки надає індекс поточного елементу

    Для Map:
    filterKeys - фільтрує Map за ключами.
    filterValues - фільтрує Map за значеннями.
    mapKeys - змінює ключі Map, залишаючи значення.
    mapValues - змінює значення Map, залишаючи ключі.

    all - перевіряє, чи всі елементи відповідають умові.
    any - перевіряє, чи хоча б один елемент відповідає умові.
*/

data class Student(
    val name: String,
    val age: Int,
    val grade: Int
)

val students = listOf(
    Student("Anna", 22, 95),
    Student("Oleg", 20, 78),
    Student("Sofia", 23, 88),
    Student("Max", 21, 65),
    Student("Olivia", 24, 92)
)
val grades = mapOf(
    "Anna" to 95,
    "Oleg" to 78,
    "Sofia" to 88,
    "Max" to 65,
    "Olivia" to 92
)

fun main(){
    // Частина 1
    // список імен усіх студентів
    val names = students.map (Student::name).also { println(it) }

    // список студентів з балом 80 і вище
    val excellentStudents = students.filter { it.grade >= 80 }.also { println(it) }

    // список імен студентів з балом 80 та вище
    val excellentStudentsNames = excellentStudents.map(Student::name).also { println(it) }

    // список імен з оцінкою
    val studentList = students.map{
        "${it.name}: ${it.grade}"
    }
    studentList.forEach { println(it) }

    // список імен з індексами
    val indexList = students.mapIndexed { index, student ->
        "$index: ${student.name}"
    }
    indexList.forEach { println(it) }

    // студенти на парних індексах
    val evenStudents = students.filterIndexed{index, _ ->
        index % 2 == 0
    }.also { println(it) }

    // студенти на непарних індексах
    val oddStudents = students.filterIndexed{index, _ ->
        index % 2 != 0
    }.also { println(it) }

    // перевірка чи всі студенти мають оцінку не менше 60
    val studentsHasMinGrade = students.all { it.grade >= 60 }.also { println(it) }

    // перевірка чи є хоча б один студент з оцінкою більше 90
    val isPerfectStudent = students.any{it.grade>90}.also { println(it) }

    // Частина 2
    // отримай Map, де всі оцінки збільшені на 5.
    val gradesPlusFiveMap = grades.mapValues{
        it.value+5
    }.also { println(it) }

    // отримай Map тільки зі студентами, які мають оцінку 80 або більше.
    val excellentStudentsMap = grades.filterValues {
        it >= 80
    }.also { println(it) }

    // отримай Map тільки зі студентами, чиє ім'я починається на A або O.
    val filteredNamesMap = grades.filterKeys {
        it.isNotEmpty() && (it[0] == 'A' || it[0] == 'O')
    }.also { println(it) }

    // перетвори Map так, щоб імена студентів стали значеннями, а оцінки — ключами.
    val reversedMap = grades.map{ (key, value)->
        value to key
    }.toMap().also { println(it) }

    // створи Map, де до кожного імені додається "Student: ":
    val editedMap = grades.mapKeys{
        "Student: ${it.key}"
    }.also { println(it) }

    // отримай список тільки оцінок студентів.
    val gradesList = grades.values.toList().also { println(it) }

    // отримай список тільки імен студентів.
    val namesList = grades.keys.toList().also { println(it) }
}