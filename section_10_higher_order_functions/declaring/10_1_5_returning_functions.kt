package kotlin_in_action.section_10_higher_order_functions.declaring

import kotlin_in_action.Product
import kotlin_in_action.products

// 10.1.5 - Returning functions from functions

/*
    У Kotlin функції можуть повертати інші функції, зазначаючи функціональний тип як тип результату.
    Повернену функцію можна зберігати у змінній, викликати багаторазово або передавати як аргумент іншій функції.
    Такий підхід використовується для динамічного формування предикатів, вибору алгоритмів та реалізації патерну Strategy.
*/

// Завдання 1: Реалізувати вибір стратегії розрахунку
// комісії залежно від способу оплати шляхом повернення функції.
enum class PaymentMethod{ CARD, PAYPAL, BANK_TRANSFER, CASH }

fun getCommissionCalculator(
    paymentMethod: PaymentMethod
): (Double) -> Double {
    return when (paymentMethod) {
        PaymentMethod.CARD -> { price -> price * 2/100 }
        PaymentMethod.PAYPAL -> { price -> price * 3/100 + 5 }
        PaymentMethod.BANK_TRANSFER -> { _ -> 10.0}
        PaymentMethod.CASH -> { _ -> 0.0 }
    }
}

// Завдання 2: Реалізувати динамічне формування предиката
// на основі налаштувань фільтрації та передавати
// отриманий предикат у функцію пошуку товарів.
class ProductFilterSettings(
    val nameQuery: String = "",
    val minPrice: Int? = null,
    val maxPrice: Int? = null
){
    fun getPredicate(): (Product) -> Boolean {
        return { product ->
            product.name.contains(nameQuery, ignoreCase = true) &&
                    (minPrice == null || product.price >= minPrice) &&
                    (maxPrice == null || product.price <= maxPrice)
        }
    }
}

fun searchProducts(
    products: List<Product>,
    predicate: (Product) -> Boolean
): List<Product>{
    return products.filter(predicate)
}

fun main() {
    println("Task 1")
    val paymentMethod = PaymentMethod.CARD
    val commissionCalculator = getCommissionCalculator(paymentMethod)
    val product = products[0]
    println("Product ${product.name} price ${product.price}: you should pay ${commissionCalculator(product.price.toDouble())} commission")

    println("\nTask 2")
    val filterSettings = ProductFilterSettings(
        nameQuery = "M", minPrice = 1000, maxPrice = null
    )
    val filterSettings1 = ProductFilterSettings(
        maxPrice = 20000, minPrice = 1000
    )
    println(searchProducts(products, filterSettings.getPredicate()))
    println(searchProducts(products, filterSettings1.getPredicate()))
}