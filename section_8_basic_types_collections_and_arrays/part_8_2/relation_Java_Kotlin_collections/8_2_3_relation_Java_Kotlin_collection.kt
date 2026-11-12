package kotlin_in_action.section_8_basic_types_collections_and_arrays.part_8_2.relation_Java_Kotlin_collections

// 8.2.3 - Kotlin and Java collections are deeply related

/*
    Kotlin на JVM використовує Java Collections Framework, тому колекції Kotlin
    і Java сумісні та можуть передаватися між ними.
    - Java: List, Set, Map — інтерфейси, які дозволяють змінювати колекції.
    - Kotlin: розділяє інтерфейси на read-only (List, Set, Map) та mutable (MutableList, MutableSet, MutableMap).
    - Java може змінити Kotlin List і тд.
 */

// Завдання — Передай Kotlin-колекцію в Java-метод (ProductStorage.addProduct()), який додає елементи.
// Перевір, як Java може змінювати список навіть через read-only інтерфейс Kotlin.
fun main(){
    val productList = mutableListOf("Laptop", "Mouse")
    ProductStorage.addProduct(productList, "Keyboard")
    println(productList)

    val immutableProductList: List<String> = productList
    ProductStorage.addProduct(immutableProductList, "Monitor")
    println(immutableProductList)
}