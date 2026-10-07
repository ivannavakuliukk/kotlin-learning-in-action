package kotlin_in_action

data class Product(
    val name: String,
    val price: Int
)

val products = listOf(
    Product("Laptop", 40000),
    Product("Mouse", 800),
    Product("Keyboard", 2500),
    Product("Monitor", 12000),
    Product("Headphones", 3000),
    Product("Webcam", 2000)
)