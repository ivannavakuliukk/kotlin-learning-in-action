package kotlin_in_action.section_9_operators_and_conventions

data class Point(val x: Int, val y: Int): Comparable<Point> {

    override fun compareTo(other: Point): Int {
        return compareValuesBy(this, other, Point::x, Point::y)
    }
}