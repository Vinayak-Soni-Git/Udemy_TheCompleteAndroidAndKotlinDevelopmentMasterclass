package Section_14_Collections

fun main() {
    val numbers = listOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
    println(numbers[2][2])

    val numbersSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(7, 8, 9))

    val numbersFlatten = numbersSets.flatten()
    for (number in numbersFlatten) {
        println(number)
    }
}