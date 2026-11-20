package kotlin_in_action.section_9_operators_and_conventions

data class CartItem(val name: String, val price: Money)

// Завдання 2 з 9.1.2, Завдання 2 з 9.1.3
class ShoppingCart(
    private val items: MutableList<CartItem> = mutableListOf()
){
    fun getItems(): List<CartItem> = items.toList()

    operator fun plusAssign(item: CartItem) {
        items.add(item)
    }

    operator fun minusAssign(item: CartItem) {
        items.remove(item)
    }

    // Якщо одночасно доступні plus() та plusAssign(),
    // для var cart виникає неоднозначність оператора +=.
    // Для val cart використовується plusAssign().
    operator fun plus(item: CartItem): ShoppingCart {
        val newItems = items.toMutableList()
        newItems.add(item)
        return ShoppingCart(newItems)
    }

    operator fun not(): Boolean = items.isEmpty()

}