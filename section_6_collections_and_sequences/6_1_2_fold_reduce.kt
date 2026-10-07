package kotlin_in_action.section_6_collections_and_sequences

/*
    reduce - послідовно об'єднує всі елементи колекції
    та повертає одне фінальне значення.
    Перший елемент використовується як початкове значення acc (акумулятора).
    fold - працює так само, як reduce, але дозволяє
    задати власне початкове значення acc.

    runningReduce - як reduce, але повертає всі проміжні
    результати накопичення.
    runningFold - як fold, але повертає всі проміжні
    результати, включаючи початкове значення.
 */

fun main() {

    // 1. Є список витрат за тиждень.
    val expenses = listOf(120, 80, 250, 100, 50)
    // Порахуй загальну суму витрат
    val summed = expenses.reduce{ acc, element-> acc + element}.also { println(it) }


    // 2. Є список оцінок за контрольні роботи.
    // Порахуй добуток усіх оцінок.
    val scores = listOf(85L, 90L, 78L, 95L, 88L)
    val multiplied = scores.reduce { acc, element ->
        acc * element
    }
    println(multiplied)


    // 3. Є список щоденних змін температури.
    val temperatureChanges = listOf(2, -3, 5, -2, 4)
    // Покажи, як змінювалася загальна температура після кожної зміни.
    val changesValues = temperatureChanges.runningReduce { acc, element -> acc + element}.also { println(it) }


    // 4. У тебе є банківський рахунок.
    // Початковий баланс — 1000.
    val transactions = listOf(-200, 500, -150, 300, -100)
    // Покажи баланс після кожної операції, враховуючи початкові 1000.
    val balanceHistory = transactions.runningFold(1000){acc, element-> acc+ element}
    println(balanceHistory)


    // 5. Є список балів, які користувач отримав за виконання завдань.
    val points = listOf(10, 25, 15, 30, 20)
    // Знайди найбільше значення у списку, використовуючи накопичення.
    val maxPoint = points.reduce{ acc, element ->
        if(acc > element) acc else element
    }
    println(maxPoint)


    // 6. Є список змін кількості товарів на складі.
    val stockChanges = listOf(-5, 10, -3, -7, 15)
    // Початкова кількість товарів — 50.
    // Покажи кількість товарів на складі після кожної зміни.
    val stockHistory = stockChanges.runningFold(50){acc, element -> acc+ element}
    println(stockHistory)


    // 7. Є список чисел.
    val numbers = listOf(2, 3, 4, 5)
    // Створи список, у якому кожне наступне значення
    // є результатом множення попереднього накопиченого значення на наступне число.
    val multipliedNumbers = numbers.runningReduce {acc, element -> acc*element}
    println(multipliedNumbers)

    // 8. Є список пожертв.
    val donations = listOf(100, 250, 50, 300, 150)
    // Порахуй загальну суму пожертв, але початкове значення вже становить 500.
    val donationsSum = donations.fold(500){acc, element -> acc + element}
    println(donationsSum)
}