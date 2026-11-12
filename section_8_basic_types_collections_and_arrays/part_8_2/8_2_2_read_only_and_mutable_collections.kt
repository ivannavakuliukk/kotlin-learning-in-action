package kotlin_in_action.section_8_basic_types_collections_and_arrays.part_8_2

import kotlin_in_action.Product
import kotlin_in_action.products

// 8.2.2 Read-only and mutable collections

/*
    List, Set, Map — read-only колекції, MutableList, MutableSet, MutableMap — дозволяють змінювати вміст.
    Read-only не означає immutable: колекція може змінюватися через інше посилання.
    Defensive copy — створення незалежної копії колекції (toList()), щоб зовнішні зміни не впливали на її стан.
    toList() створює поверхневу копію (shallow copy): об'єкти всередині залишаються спільними.
 */

// Завдання — ShoppingCart та Order
// Реалізуй кошик із можливістю додавання, видалення товарів та оформлення замовлення.
// Використай defensive copy, щоб зміни кошика не впливали на оформлене замовлення.
class ShoppingCart {
    private val _products = mutableListOf<Product>()
    fun addProduct(product: Product) = _products.add(product)

    fun removeProduct(name: String): Boolean{
        val searchProduct = _products.find { it.name == name }
        if(searchProduct != null) {
            _products.remove(searchProduct)
            return true
        }
        else return false
    }

    fun getProducts(): List<Product>{
        return _products
    }

    fun checkout(): Order{
        return Order(shoppingCartProducts = _products)
    }

}

class Order(shoppingCartProducts: List<Product>) {
    // Створюємо defensive copy - без неї при зміні кошика буде змінюватись і замовлення
    // - бо буде посилання на один об'єкт
    private val products = shoppingCartProducts.toList()
    fun calculateTotal(): Int = products.sumOf { it.price }
    fun getProducts(): List<Product> = products
}

fun main(){
    val shoppingCart = ShoppingCart()

    shoppingCart.addProduct(products[0])
    shoppingCart.addProduct(products[1])
    shoppingCart.addProduct(products[2])
    println("Shopping cart: ${shoppingCart.getProducts()}")

    shoppingCart.removeProduct("Mouse")
    println("Shopping cart: ${shoppingCart.getProducts()}")

    val order = shoppingCart.checkout()
    println("Order: ${order.getProducts()}")
    println("Order sum: " + order.calculateTotal())

    shoppingCart.removeProduct("Laptop")
    shoppingCart.addProduct(products[3])
    println("Shopping cart: ${shoppingCart.getProducts()}")

    println("Order: ${order.getProducts()}")
    println("Order sum: " + order.calculateTotal())
}