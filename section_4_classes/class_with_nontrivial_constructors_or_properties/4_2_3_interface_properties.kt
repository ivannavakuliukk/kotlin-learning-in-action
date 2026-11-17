package kotlin_in_action.section_4_classes.class_with_nontrivial_constructors_or_properties

// 4.2.3 - Implementing properties declared in interfaces

/*
У Kotlin інтерфейси можуть оголошувати властивості (val, var), але не можуть зберігати їхні значення у backing field. Класи, які реалізують інтерфейс, повинні надати реалізацію абстрактних властивостей.
    Способи реалізації властивостей:
    - Через primary constructor — override val/var одразу оголошує та ініціалізує властивість.
    - Через тіло класу — властивість оголошується та ініціалізується окремо.
    - Через custom getter — значення обчислюється при кожному зверненні, без backing field, якщо getter не використовує field.
    Інтерфейс також може містити getter із готовою реалізацією, який класи можуть використовувати або перевизначати.
 */

// Завдання 1: Створити інтерфейс User із властивістю nickName та реалізувати її трьома способами:
// через primary constructor, обчислюваний getter і ініціалізацію властивості в тілі класу.
interface User{
    val nickName: String
}

class PrivateUser(override val nickName: String) : User

// так як це property with custom accessor - воно не зберігає значення в backing field,
// а обраховується щоразу при зверненні
class EmailUser(val email: String):User{
    override val nickName: String
        get() = email.substringBefore('@')
}

class SocialUser(accountId: Int) : User{
    override val nickName: String = "user_$accountId"
}


// Завдання 2: Створити інтерфейс Sellable із властивостями name, price та getter для priceWithTax.
// Реалізувати звичайний товар і товар зі знижкою, перевизначивши розрахунок ціни з податком.
interface Sellable {
    val name: String
    var price: Int
    val priceWithTax: Int
        get() = price + price * 20 / 100
}

class StoreProduct(override val name: String, override var price: Int): Sellable

class DiscountedProduct(override val name: String, override var price: Int): Sellable{
    override val priceWithTax : Int
        get() {
            val discountedPrice = price - price * 10 / 100
            return discountedPrice + discountedPrice * 20 / 100
        }
}

fun main(){
    println("Task 1")
    val usersList: List<User> = listOf(
        PrivateUser("John"),
        EmailUser("john.doe"),
        SocialUser(123)
    )
    usersList.forEach{
        println(it.nickName)
    }


    println("\nTask 2")
    val product = StoreProduct("Bread", 50)
    val discountedProduct = DiscountedProduct("Bread", 50)
    println("Product: ${product.priceWithTax}, Discounted product: ${discountedProduct.priceWithTax}")

    product.price = 100
    discountedProduct.price = 100
    println("Product: ${product.priceWithTax}, Discounted product: ${discountedProduct.priceWithTax}")
}