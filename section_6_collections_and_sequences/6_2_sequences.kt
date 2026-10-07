package kotlin_in_action.section_6_collections_and_sequences

import kotlin_in_action.Product
import kotlin_in_action.products

// 6.2 - Lazy collection operations: Sequences

/*
    Sequence — це послідовність елементів, яка дозволяє виконувати операції над колекцією ліниво (lazy),
    тобто лише тоді, коли потрібен кінцевий результат.
    Звичайні колекції працюють eager — кожна операція виконується
    одразу та може створювати проміжну колекцію.
    У Sequence операції об'єднуються в один ланцюжок і виконуються поелементно,
    що може бути ефективніше для великих колекцій або довгих ланцюжків.

    Intermediate operations — формують або змінюють Sequence і
    не запускають її виконання: filter, map, take, sorted тощо.

    Terminal operations — запускають виконання Sequence і повертають
    кінцевий результат: toList(), first(), count(), sum() тощо.

    Створити sequence можна 3-ьома способами: toSequence, sequenceOf, generateSequence
 */

fun main(){
    // Зроби два варіанти отримання назв перших 2 товарів, які коштують більше 2000:
    //використовуючи звичайний List;
    //використовуючи Sequence.
    println("Task 1")
    val firstExpensive1 = products
        .filter{it.price > 2000}
        .map(Product::name)
        .take(2)
    println(firstExpensive1)
    val firstExpensive2 = products
        .asSequence()
        .filter{it.price > 2000}
        .map{it.name}
        .take(2)
        .toList() // якщо закоментувати, термінальна операція не виконається - результату не буде
    println(firstExpensive2)

    //Створи Sequence<Int> трьома різними способами:
    //перетворивши вже існуючий список;
    //використовуючи sequenceOf();
    //використовуючи generateSequence()
    println("Task 2")
    val evenNumbersList = listOf(2, 4, 6, 8, 10)
    val evenNumbersSequence1 = evenNumbersList.asSequence()
    val evenNumbersSequence2 = sequenceOf(2, 4, 6, 8, 10)
    val evenNumbersSequence3 = generateSequence(2) { it + 2 }
    println(evenNumbersSequence1.toList())
    println(evenNumbersSequence2.toList())
    println(evenNumbersSequence3.takeWhile { it <= 10 }.toList())

    /*
        1. Спробуй до запуску коду написати, що буде виведено в консоль.
        Відповідь: filter 1 filter 2 map 2 filter 3 filter 4 map 4
        2. Скільки разів виконається filter? (Відповідь 4)
        3. Скільки разів виконається map? (Відповідь 2)
        4. Чому filter і map не обробляють усі 5 елементів?
        Відповідь: Sequence обробляє елементи по одному через увесь ланцюжок,
        а не виконує спочатку весь filter, потім весь map. Коли take(2) отримав 2 елементи,
        подальша обробка припиняється.
        5. Яка тут intermediate operation, а яка terminal operation?
        Відповідь: filter, map, take - intermediate, toList - terminal
     */
    println("Task 3")
    val numbers = listOf(1, 2, 3, 4, 5)

    val result = numbers
        .asSequence()
        .filter {
            println("filter: $it")
            it % 2 == 0
        }
        .map {
            println("map: $it")
            it * 10
        }
        .take(2)
        .toList()
}