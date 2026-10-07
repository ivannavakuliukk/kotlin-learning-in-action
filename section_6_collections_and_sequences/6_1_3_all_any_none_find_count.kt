package kotlin_in_action.section_6_collections_and_sequences

// 6.1.3 - applying predicate to the collection - all, any, non, count, find
/*
    all → перевірити, чи ВСІ відповідають умові
    any → перевірити, чи ХОЧА Б ОДИН відповідає умові
    none → перевірити, чи ЖОДЕН не відповідає умові
    count → ПОРАХУВАТИ елементи, які відповідають умові
    find → ЗНАЙТИ ПЕРШИЙ елемент, який відповідає умові
*/

data class Order(
    val customer: String,
    val amount: Int,
    val status: String
)

val orders = listOf(
    Order("Anna", 250, "PAID"),
    Order("Oleg", 120, "CANCELLED"),
    Order("Sofia", 450, "PAID"),
    Order("Max", 80, "PENDING"),
    Order("Olivia", 320, "PAID"),
    Order("Daniel", 150, "CANCELLED"),
    Order("Emma", 600, "PAID")
)

fun main() {

    // 1. Перевір, чи всі замовлення мають статус PAID.
    val isAllPaid = orders.all{it.status == "PAID"}
    println(isAllPaid)

    // 2. Перевір, чи є хоча б одне замовлення зі статусом CANCELLED.
    val isAnyCancelled = orders.any{it.status == "CANCELLED"}
    println(isAnyCancelled)

    // 3. Перевір, чи немає жодного замовлення зі статусом PENDING.
    val isNonePending = orders.none{it.status == "PENDING"}
    println(isNonePending)

    // 4. Порахуй кількість замовлень, які мають статус PAID.
    val paidCount = orders.count{it.status == "PAID"}
    println(paidCount)

    // 5. Знайди перше замовлення зі статусом CANCELLED.
    val firstCancelled = orders.find{it.status == "CANCELLED"}
    println(firstCancelled)

    // 6. Знайди перше замовлення, сума якого більша за 300.
    val firstMoreThan300 = orders.find{it.amount > 300}
    println(firstMoreThan300)

    // 7. Перевір, чи всі оплачені замовлення мають суму більше 100.
    val paidOrders = orders.filter{it.status == "PAID"}
    val isAllMoreThan100 = paidOrders.all{it.amount > 100}
    println(isAllMoreThan100)

    // 8. Перевір, чи є хоча б одне скасоване замовленн із сумою більше 100.
    val isCancelledMoreThan100 = orders.any{
        it.status =="CANCELLED" && it.amount > 100
    }
    println(isCancelledMoreThan100)

    // 9. Порахуй кількість замовлень, сума яких більша або дорівнює 250.
    val countMoreThan250 = orders.count{it.amount >=250}
    println(countMoreThan250)

    // 10. Знайди перше оплачене замовлення, сума якого більша за 400.
    val firstPaidMoreThan400 = orders.firstOrNull{
        it.status == "PAID" && it.amount > 400
    }
    println(firstPaidMoreThan400)

    // 11. Перевір, чи немає оплачених замовлень із сумою менше 100.
    val isNoneOfPaidLessThan100 = orders.none{it.status == "PAID" && it.amount < 100}
    println(isNoneOfPaidLessThan100)

    // 12. Знайди перше замовлення конкретного клієнта "Sofia".
    val firstSofiaOrder = orders.find{it.customer == "Sofia"}
    println(firstSofiaOrder)

    // 13. Порахуй, скільки замовлень не мають статусу CANCELLED.
    val countNotCancelled = orders.count{it.status != "CANCELLED"}
    println(countNotCancelled)

    // 14. Перевір, чи всі замовлення клієнтів,
    //     чиє ім'я починається з голосної,
    //     мають суму більше 100.
    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    val isOrdersVowelsAndMoreThan100 = orders
        .filter {
            it.customer[0].lowercaseChar() in vowels
        }
        .all {
            it.amount > 100
        }

    println(isOrdersVowelsAndMoreThan100)

    // 15. Знайди перше замовлення, яке одночасно:
    //     - має статус PAID;
    //     - має суму більше 300.
    val firstPaidAndMoreThan300 = orders.find{it.amount > 300 && it.status == "PAID"}
    println(firstPaidAndMoreThan300)
}