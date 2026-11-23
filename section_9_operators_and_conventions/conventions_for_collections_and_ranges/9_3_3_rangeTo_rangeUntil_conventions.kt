package kotlin_in_action.section_9_operators_and_conventions.conventions_for_collections_and_ranges

import kotlin_in_action.section_9_operators_and_conventions.Money
import kotlin_in_action.section_9_operators_and_conventions.Point


// 9.3.3 - Creating ranges from objects: The rangeTo and rangeUntil conventions

/*
    Оператори діапазонів дозволяють створювати діапазони не лише з чисел, а й із власних об'єктів.
    - a..b викликає a.rangeTo(b) — закритий діапазон, обидві межі включені.
    - a..<b викликає a.rangeUntil(b) — напіввідкритий діапазон, верхня межа виключена.
    - ClosedRange<T> — інтерфейс закритого діапазону з властивостями start та endInclusive.
    - OpenEndRange<T> — інтерфейс напіввідкритого діапазону з властивостями start та endExclusive.
    - Обидва інтерфейси мають стандартну реалізацію contains() та isEmpty() і вимагають, щоб тип T реалізовував Comparable<T>.
    - IntRange — готовий клас діапазону цілих чисел, який також підтримує ітерацію.
*/

// Завдання 1: Перевантажити rangeTo() і rangeUntil() для Money,
// створюючи числові діапазони IntRange на основі amount.
operator fun Money.rangeTo(that: Money): IntRange{
    return amount..that.amount
}

operator fun Money.rangeUntil(that: Money): IntRange{
    return amount..< that.amount
}

// Завдання 2: Реалізувати власні діапазони Point через
// ClosedRange і OpenEndRange за допомогою анонімних об'єктів.
operator fun Point.rangeTo(that: Point): ClosedRange<Point>{
    return object : ClosedRange<Point>{
        override val start: Point = this@rangeTo
        override val endInclusive: Point = that

        override fun toString(): String {
            return "(${start.x}, ${start.y}).." +
                        "(${endInclusive.x}, ${endInclusive.y})"
        }
    }
}

operator fun Point.rangeUntil(that: Point): OpenEndRange<Point>{
    return object : OpenEndRange<Point>{
        override val start: Point = this@rangeUntil
        override val endExclusive: Point = that
        override fun toString(): String {
            return "(${start.x}, ${start.y})..<" +
                    "(${endExclusive.x}, ${endExclusive.y})"
        }
    }
}

// Завдання 3: Реалізувати Comparable для Version та використати
// стандартні конвенції rangeTo() і rangeUntil().
data class Version(val major: Int, val minor: Int): Comparable<Version>{
    override fun compareTo(other: Version): Int {
        return compareValuesBy(this, other, Version::major, Version::minor)
    }

}

fun main() {
    println("Task 1")
    val moneyRangeClosed = Money(100) .. Money(120)
    val moneyRangeOpenEnded = Money(100) ..< Money(120)

    println("Closed $moneyRangeClosed: 120 in range - ${120 in moneyRangeClosed}")
    println("Open-ended $moneyRangeOpenEnded: 120 in range - ${120 in moneyRangeOpenEnded}")


    println("\nTask 2")
    val point1 = Point(0, 0)
    val point2 = Point(10, 10)

    val closedPointRange = point1 .. point2
    println("Closed $closedPointRange: $point2 not in range - ${point2 !in closedPointRange} ")

    val openEndedPointRange = point1 ..< point2
    println("Open-ended: $openEndedPointRange: $point2 in range - ${point2 in openEndedPointRange}")


    println("\nTask 3")
    val version1 = Version(major = 1, minor = 0)
    val version2 = Version(major = 3, minor = 8)
    val version3 = Version(major = 2, minor = 230)
    val version4 = Version(major = 3, minor = 9)
    val closedVersionRange = version1 .. version2

    println("Closed: $closedVersionRange - $version3 in range - ${version3 in closedVersionRange}")
    println("Closed: $closedVersionRange - $version4 in range - ${version4 in closedVersionRange}")
}