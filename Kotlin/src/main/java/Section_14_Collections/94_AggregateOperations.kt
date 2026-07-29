package Section_14_Collections

fun main() {
    val numbers = listOf(6, 10, 14, 4, 100)
    println(numbers.sum())
    println(numbers.count())
    println(numbers.average())
    println(numbers.maxOrNull())
    println(numbers.minOrNull())

    println(numbers.sumOf { it * 2 })
}