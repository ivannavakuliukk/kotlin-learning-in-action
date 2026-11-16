package kotlin_in_action.section_8_basic_types_collections_and_arrays.basic_types

// 8.1.4 - Kotlin makes number conversions explicit

/*
    - Kotlin вимагає явного перетворення числових типів при присвоєнні,
      передачі змінних у функції та порівнянні (toInt(), toLong() тощо).
    - Числові літерали можуть набувати очікуваного типу без перетворення:
      val n: Long = 100 або функція з параметром Long приймає 100.
    - Арифметичні операції між деякими різними числовими типами не потребують явного перетворення.
    - Overflow/underflow — вихід за межі діапазону типу.
      Int.MAX_VALUE + 1 → Int.MIN_VALUE.
      Щоб уникнути переповнення, перетворюй на Long ДО обчислення.
    - String → число: toInt(), toDouble() або безпечні
      toIntOrNull(), toDoubleOrNull() (повертають null при помилці).
 */

//  Завдання 1 - Явне перетворення при присвоєнні та порівнянні
//  Створи функцію compareNumbers(number: Int, limit: Long): Boolean,
//  яка повертає true, якщо число більше за ліміт.
//  Додатково створи змінну Int та спробуй присвоїти її значенні змінній Long

fun compareNumbers(number: Int, limit: Long): Boolean{
    return number.toLong() > limit
}

//  Завдання 2 - Числові літерали та змінні
//  Створити функцію, яка повертає загальну вартість покупки
//      1. Передай звичайні числові літерали.
//      2. Використай літерал із суфіксом L.
//      3. Створи змінну з явним типом Int і передай у функцію

fun calculateTotal(price: Long, quantity: Int): Long{
    return quantity * price
}

// Завдання 3 - Перетворення String
// Напиши функцію, яка приймає два рядки та намагається повернути Double - середнє арифметичне
fun calculateAverage(first: String, second: String): Double?{
    val firstNumber = first.toDoubleOrNull()
    val secondNumber = second.toDoubleOrNull()
    return if(firstNumber == null || secondNumber == null) null
    else (firstNumber+secondNumber)/2
}

fun main(){
    println("Task 1")
    println(compareNumbers(150, 100L))
    println(compareNumbers(50, 100L))
    val number: Int = 150
//    так буде помилка
//    val convertedNumber: Long = number
    val convertedNumber: Long = number.toLong()
    println(convertedNumber)

    println("\nTask 2")
    println(calculateTotal(100, 3))
    println(calculateTotal(100L, 3))
    val price: Int = 100
//    так буде помилка - треба явне перетворення типів
//    println(calculateTotal(price, 3))
    println(calculateTotal(price.toLong(), 3))

    println("\nTask 3")
    println(calculateAverage("10", "20"))
    println(calculateAverage("12.5", "7.5"))
    println(calculateAverage("abc", "20"))
}
