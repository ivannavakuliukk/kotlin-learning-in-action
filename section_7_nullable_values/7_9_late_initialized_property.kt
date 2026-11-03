package kotlin_in_action.section_7_nullable_values

import kotlin_in_action.Product
import kotlin_in_action.products

// 7.9 - Non-null types without immediate initialization: Late initialized properties

/*
    lateinit var — дозволяє оголосити змінну без початкового значення та ініціалізувати її пізніше,
    до першого звернення. Використовується лише з var і не може зберігати nullable-значення.

    ::ім'яВластивості.isInitialized — перевіряє, чи була ініціалізована властивість, оголошена через lateinit var.
     Використовується перед зверненням до такої властивості, щоб уникнути UninitializedPropertyAccessException.
 */

// Завдання 1 (з класом Product)
// Створи клас StoreManager, який відповідає за роботу з товарами. Список товарів має
// передаватися в менеджер не під час створення об'єкта, а пізніше — через окремий метод ініціалізації.
// Додати метод для знаходження найдорожчого товару та врахувати що він може бути викликаним перед ініціалізацією

class StoreManager(){
    private lateinit var products : List<Product>

    fun setUp(products: List<Product>) {
        this.products = products
    }

    fun getMostExpensiveProduct() : Product? {
        if(!::products.isInitialized) return null
        return products.maxBy { it.price }
    }
}

// Завдання 2 (з класом Product)
// Показати як вирішувалося б завдання без lateinit

class StoreManagerFake(){
    private var products: List<Product>? = null

    fun setUp(products: List<Product>) {
        this.products = products
    }
    fun getMostExpensiveProduct() : Product? {
        return if(products == null) null else products!!.maxBy { it.price }
    }
}

fun main(){
    println("Task 1")
    val storeManager = StoreManager()
//    storeManager.setUp(products = products)
    println(storeManager.getMostExpensiveProduct())

    println("\nTask 2")
    val storeManagerFake = StoreManagerFake()
//    storeManagerFake.setUp(products = products)
    println(storeManagerFake.getMostExpensiveProduct())
}