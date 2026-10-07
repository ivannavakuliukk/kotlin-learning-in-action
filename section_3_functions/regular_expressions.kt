package kotlin_in_action.section_3_functions

// with regular expressions
fun parsePathRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)

    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Каталог: $directory, ім'я: $filename, розширення: $extension")
    }
}

// with string methods
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}


fun main() {
    parsePathRegex("/Users/yole/kotlin-book/chapter.adoc")
    // Каталожка: /Users/yole/kotlin-book, ім'я: chapter, розширення: adoc
    parsePath("/Users/yole/kotlin-book/chapter.adoc")

}