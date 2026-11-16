package kotlin_in_action.section_4_classes.defining_class_hierarchies

// 4.1.2 - Open, final and abstract modifiers: Final by default

/*
    У Kotlin класи, методи та властивості за замовчуванням final.
    - open — дозволяє успадковувати класи та перевизначати їхні методи й властивості.
    - final — забороняє успадкування або перевизначення (за замовчуванням).
    - abstract — позначає клас, об'єкт якого не можна створити напряму, або метод чи властивість без реалізації, яку мають
     реалізувати нащадки. Використовується в абстрактних класах та інтерфейсах (в інтерфейсах не пишеться явно)
    - override — перевизначає метод або властивість; перевизначений член залишається відкритим, якщо не вказати final override.
 */

// Завдання 1: Створити базовий клас Character та перевизначити атаку для Warrior і Archer.
// Використати open для успадкування, залишивши showInfo() final.
open class Character(
    val name: String,
    val damage: Int
){
    open fun attack() =  println("$name attacks for $damage damage")
    fun showInfo() =  println("$name: $damage damage")
}

class Warrior(name: String, damage: Int): Character(name, damage){
    override fun attack() {
        println("$name attacks for ${damage*2} damage")
    }
}

class Archer(name: String, damage: Int): Character(name, damage){
    override fun attack() {
        println("$name attacks for ${damage+5} damage")
    }
}


// Завдання 2: Створити абстрактний клас Ability з методом use().
// Реалізувати різні здібності та викликати їх через спільний тип Ability.
abstract class Ability(val name: String, val manaCost: Int){
    abstract fun use(): Int
    fun showInfo() = println("$name mana for $manaCost costs")
}

class Fireball(name: String, manaCost: Int): Ability(name, manaCost){
    override fun use(): Int  = manaCost * 3
}

class Healing(name: String, manaCost: Int): Ability(name, manaCost){
    override fun use(): Int  = manaCost * 2
}


// Завдання 3: Створити ієрархію Enemy → Boss → SuperBoss зі зменшенням отриманої шкоди.
// Використати final override, щоб заборонити подальше перевизначення takeDamage().
open class Enemy {
    open fun takeDamage(amount: Int) {
        println("Enemy takes $amount damage")
    }
}

open class Boss: Enemy(){
    override fun takeDamage(amount: Int) {
        println("Enemy takes ${amount/2} damage")
    }
}

open class SuperBoss: Boss(){
    final override fun takeDamage(amount: Int) {
        println("Enemy takes ${amount/4} damage")
    }
}

fun main(){
    println("Task 1")
    val warrior = Warrior("Archi", 50)
    warrior.showInfo()
    warrior.attack()

    val archer = Archer("Bob", 100)
    archer.showInfo()
    archer.attack()


    println("\nTask 2")
    val manaList: List<Ability> = listOf(
        Fireball("Hot fireball", 100),
        Healing("Super healing", 100),
    )
    manaList.forEach{
        it.showInfo()
        println(it.use())
    }


    println("\nTask 3")
    val enemy = Enemy()
    enemy.takeDamage(300)
    val superBoss = SuperBoss()
    superBoss.takeDamage(300)
}

