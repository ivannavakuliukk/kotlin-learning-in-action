package kotlin_in_action.section_4_classes.class_with_nontrivial_constructors_or_properties

// 4.2.4 - Accessing a backing fields from a getter or setter

/*
Backing field — приховане поле, у якому Kotlin зберігає значення властивості.
Для доступу до нього всередині get() і set() використовується спеціальний ідентифікатор field.
    - get() — дозволяє налаштувати читання властивості, використовуючи field для отримання збереженого значення.
    - set(value) — дозволяє перевіряти або змінювати значення перед записом у field.
    - field — звертається безпосередньо до backing field, не викликаючи getter або setter повторно(бо це викликає нескінченну рекурсію).
    - Без field — властивість з обчислюваним getter може не мати backing field.
 */

// Завдання 1: Створити AudioPlayer із custom setter для volume.
// Обмежувати гучність діапазоном 0..100 через coerceIn() та змінювати значення через field.
class AudioPlayer{
    var volume: Int = 50
        set(value){
            val formattedValue = if(value !in 0..100){
                value.coerceIn(0..100)
            } else value
            println("Volume changed from $field to $formattedValue")
            field = formattedValue
            // volume = formattedValue // Помилка: повторно викликає setter
        }
}

// Завдання 2: Створити TemperatureSensor із custom getter і setter.
// Перевіряти допустиму температуру через require(), зберігати й повертати значення через field.
class TemperatureSensor{
    var temperature: Double = 20.0
        set(value){
            require(value in -50.0 .. 100.0)
            field = value
        }
        get() {
            println("Reading temperature.. $field")
            return field
        }

}

fun main(){
    println("Task 1")
    val player = AudioPlayer()
    player.volume = 80
    player.volume = 150
    player.volume = -20
    println(player.volume)

    println("\nTask 2")
    val sensor = TemperatureSensor()
    sensor.temperature = 36.5
    println(sensor.temperature)
    sensor.temperature = 150.0
}