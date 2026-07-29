package Section_14_Collections

fun main() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.slice(1..3))
    println(numbers.slice(0..4 step 2))
    println(numbers.slice(setOf(3, 5, 0)))

    println(numbers.take(3))
    println(numbers.takeLast(3))
    println(numbers.drop(1))
    println(numbers.dropLast(5))

    println(numbers.takeWhile { !it.startsWith("f") })
    println(numbers.takeLastWhile { it != "three" })
    println(numbers.dropWhile { it.length == 3 })
    println(numbers.dropLastWhile { it.contains("i") })

    val intNumbers = (0..13).toList()
    println(intNumbers.chunked(3))
    println(intNumbers.chunked(3) { it.sum() })

    val numbersString2 = numbers
    println(numbersString2.windowed(3))


}