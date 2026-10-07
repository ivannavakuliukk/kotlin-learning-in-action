package kotlin_in_action.section_6_collections_and_sequences

// 6.1.11 - Processing elements in nested collections - flatMap, flatten

/*
    flatten - "розрівнює", прибирає вкладення в колекції (list of lists)
    flatMap - трансформує колекцію як і map і прибирає вкладення

    Додатково використано у завданні:

    groupingBy — це функція розширення на колекції, яка не виконує групування одразу,
    а створює об'єкт Grouping — своєрідний "план дій": за яким ключем розбити елементи на групи.
    Аргумент — лямбда, яка для кожного елемента визначає його ключ групи

    eachCount()
    Це термінальна операція над Grouping, яка рахує кількість елементів у кожній групі
    й повертає результат як Map<K, Int>, де K — тип ключа групи, а значення — скільки елементів
    опинилось у цій групі.
 */

data class Request(
    val customer: String,
    val products: List<String>
)

val requests = listOf(
    Request("Anna", listOf("Laptop", "Mouse")),
    Request("Oleh", listOf("Phone", "Case", "Charger")),
    Request("Sofia", listOf("Keyboard", "Mouse")),
    Request("Max", listOf("Monitor", "Keyboard"))
)

fun main(){
    val nested = requests.map { it.products }
    // Отримати єдиний плаский список з всіх продуктів
    println("Task 1")
    val productList = nested.flatten()
    println(productList.joinToString())

    // Отримай той самий плаский список продуктів,
    // але одним викликом напряму на requests (без проміжного map)
    println("\nTask 2")
    val productList2 = requests.flatMap{it.products}
    println(productList2.joinToString())

    // Побудуй Map<String, Int>, де ключ — назва продукту,
    // а значення — скільки разів він зустрічається серед усіх замовлень.
    println("\nTask 3")
    val productWithCount = requests.flatMap{it.products}
        .groupingBy{it}
        .eachCount()
    println(productWithCount.toString())

}