package Section_14_Collections

fun main() {
    val numbers = listOf("One", "Two", "Three", "Four")
    println(numbers.any { it.startsWith("e") })
    println(numbers.none { it.endsWith("e") })
    println(numbers.all { it.length > 1 })
}
