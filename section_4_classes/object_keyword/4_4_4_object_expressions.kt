package kotlin_in_action.section_4_classes.object_keyword

// 4.4.4 - Object expressions: Anonymous inner classes rephrased

/*
    object expression — конструкція Kotlin для створення анонімного об'єкта без оголошення окремого іменованого класу.
     Використовується, коли потрібна одноразова реалізація інтерфейсу або класу.
    - Анонімний об'єкт створюється під час виконання виразу, на відміну від Singleton (object declaration).
    - Може реалізовувати декілька інтерфейсів, успадковувати клас, містити власні властивості та методи.
    - Може захоплювати й змінювати локальні змінні зовнішньої функції.
    - Часто використовується для Listener (обробників подій).
    - Для реалізації інтерфейсу з одним методом краще SAM, для декількох методів - object expression
    - Відмінність від Java: Kotlin дозволяє змінювати захоплені локальні змінні без вимоги final.
*/

// Завдання 1: Створити анонімний об'єкт DownloadListener для обробки подій завантаження файлу.
// Реалізувати реакцію на початок, прогрес і завершення та передати слухача у downloadFile().
interface DownloadListener{
    fun onStart(fileName: String)
    fun onProgress(percent: Int)
    fun onFinish(fileName: String)
}

fun downloadFile(fileName: String, listener: DownloadListener){
    listener.onStart(fileName)
    listener.onProgress(25)
    listener.onProgress(50)
    listener.onProgress(75)
    listener.onProgress(100)
    listener.onFinish(fileName)
}

// Завдання 2: Створити анонімний Comparator<StaffMember> для сортування працівників за спаданням зарплати.
// Використати захоплення локальної змінної для підрахунку кількості викликів compare()
data class StaffMember(val name: String, val salary: Int)

fun main() {
    println("Task 1")
    val downloadObject = object: DownloadListener{
        override fun onStart(fileName: String) {
            println("Started downloading $fileName")
        }

        override fun onProgress(percent: Int) {
           println("Progress $percent%")
        }

        override fun onFinish(fileName: String) {
            println("Finished downloading $fileName")
        }

    }
    downloadFile("Settings.txt", downloadObject)

    println("\nTask 2")
    val staffMembers= listOf(
        StaffMember("Marry", 1000),
        StaffMember("John", 500),
        StaffMember("Peter", 2000),
        StaffMember("Bob", 700),
    )
    var comparisonCount = 0
    val comparatorObject = object: Comparator<StaffMember>{
        override fun compare(
            o1: StaffMember,
            o2: StaffMember
        ): Int {
            comparisonCount++
            return o2.salary.compareTo(o1.salary)
        }

    }
    val sortedList = staffMembers.sortedWith(comparatorObject)
    println("Sorted list is $sortedList,\namount of comparisons: $comparisonCount")
}