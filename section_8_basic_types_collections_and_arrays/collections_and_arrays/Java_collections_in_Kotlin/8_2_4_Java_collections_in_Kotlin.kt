package kotlin_in_action.section_8_basic_types_collections_and_arrays.collections_and_arrays.Java_collections_in_Kotlin

import kotlin_in_action.Product

// 8.2.4 - Collections declared in Java are seen as a platform types in Kotlin

/*
    Колекції, оголошені в Java, у Kotlin сприймаються як platform types: компілятор не знає напевно їхню nullability
    та mutability. Тому під час реалізації Java-інтерфейсів або перевизначення методів у Kotlin розробник самостійно обирає
    nullable/non-null та read-only/mutable типи відповідно до контракту Java-коду.
 */

// Завдання: Реалізувати Java-інтерфейс OrderProcessor у Kotlin, самостійно визначивши nullability та mutability параметрів.
// Видалити дешеві товари, застосувати знижки, відсортувати замовлення та обчислити загальну суму з доставкою.
class CustomOrderProcessor : OrderProcessor {

    val processedOrder = mutableListOf<Product>()
    var total = 0
        private set

    override fun processOrder(
        products: MutableList<Product>,
        discounts: Map<String, Int>?,
        customerType: String?,
        deliveryFee: Int?
    ) {
        products.removeAll { it.price < 100 }

        val customerTypeDiscount = when (customerType) {
            "VIP" -> 10
            else -> 0
        }

        val productsWithDiscount = products.map { product ->
            val discount = discounts?.get(product.name) ?: 0

            product.copy(
                price = product.price *
                        (100 - discount - customerTypeDiscount) / 100
            )
        }

        processedOrder.clear()
        processedOrder.addAll(
            productsWithDiscount.sortedByDescending { it.price }
        )

        total = processedOrder.sumOf { it.price } + (deliveryFee ?: 0)
    }
}

fun main() {
    val products = mutableListOf(
        Product("Laptop", 40000),
        Product("Mouse", 800),
        Product("Keyboard", 2500),
        Product("Monitor", 12000),
        Product("Cable", 50)
    )

    val discounts = mapOf(
        "Laptop" to 15,
        "Mouse" to 5,
        "Monitor" to 20
    )

    val processor = CustomOrderProcessor()

    processor.processOrder(
        products,
        discounts,
        "VIP",
        500
    )
    println("Products: $products")
    println("Order: " + processor.processedOrder)
    println("Total: " + processor.total)

    val products2 = mutableListOf(
        Product("Keyboard", 2500),
        Product("Cable", 50)
    )
    processor.processOrder(
        products2,
        null,
        null,
        null
    )
    println("\nProducts: $products2")
    println("Order: " + processor.processedOrder)
    println("Total: " + processor.total)
}