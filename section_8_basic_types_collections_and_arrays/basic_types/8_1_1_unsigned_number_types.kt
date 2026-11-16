package kotlin_in_action.section_8_basic_types_collections_and_arrays.basic_types

// 8.1.1 - Unsigned number types

/*
    Unsigned number types — беззнакові цілочисельні типи, що зберігають числа від 0 і вище.
    UByte (8 біт), UShort (16 біт), UInt (32 біти), ULong (64 біти).
    Використовуються для роботи з бітами, масками, кольорами та двійковими даними.
    Суфікс u позначає беззнаковий числовий літерал (наприклад, 255u).
 */

//  Завдання: ColorManager
//  Створи клас ColorManager, який працює з кольором у форматі ARGB (0xAARRGGBB).
//
//  Вимоги:
//  1. Клас приймає колір типу UInt через конструктор.
//  2. Реалізуй методи:
//      - getAlpha(): UByte
//      - getRed(): UByte
//      - getGreen(): UByte
//      - getBlue(): UByte
//  3. Додай можливість змінити один компонент кольору, не змінюючи інші.
//  4. Реалізуй метод isOpaque(): Boolean, який перевіряє, чи колір повністю непрозорий (Alpha = 255).
//  5. Використовуй бітові операції:
//      and — побітове І: повертає 1, тільки якщо обидва біти дорівнюють 1.
//      or — побітове АБО: повертає 1, якщо хоча б один біт дорівнює 1.
//      shr — зсуває біти вправо на вказану кількість позицій.
//      shl — зсуває біти вліво на вказану кількість позицій.
//      inv() — інвертує біти: замінює 0 на 1, а 1 на 0.

class ColorManager(var color: UInt){

    fun getBlue(): UByte{
        // І з маскою 00 00 00 FF
        return (color and 0xFFu).toUByte()
    }

    fun getGreen(): UByte{
        // зсув вправо на 8 бітів + І з маскою 00 00 00 FF
        return(color shr 8 and 0xFFu).toUByte()
    }

    fun getRed(): UByte{
        return (color shr 16 and 0xFFu).toUByte()
    }

    fun getAlpha(): UByte{
        return (color shr 24 and 0xFFu).toUByte()
    }

    fun isOpaque():Boolean{
        return getAlpha() == 255.toUByte()
    }

    fun setRed(red: UByte){
        // маска для Red
        val mask = 0x00FF0000u
        // інверсуємо
        val inverseMask = mask.inv()

        // новий червоний - перетворюємо байт в Int та зсуваємо вліво на 16 бітів
        val newRed = red.toUInt() shl 16
        // очищаємо red з допомогою маски та І + додаємо новий з допомогою OR
        color = (color and inverseMask) or newRed

    }

}

fun main(){
    println("Task 2")
    val colorManager = ColorManager(0xFF3399CCu)
    println("blue: ${colorManager.getBlue()}")
    println("green: ${colorManager.getGreen()}")
    println("red: ${colorManager.getRed()}")
    println("alpha: ${colorManager.getAlpha()}")

    println("\nTask4")
    val first = ColorManager(0xFF3399CCu)
    val second = ColorManager(0x803399CCu)
    println(first.isOpaque())
    println(second.isOpaque())

    println("\nTask3")
    colorManager.setRed(0xAAu)
    println(colorManager.color.toString(16))
}