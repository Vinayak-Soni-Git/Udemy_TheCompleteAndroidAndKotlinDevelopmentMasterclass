package Section_14_Collections

fun main() {
    val numbersString = listOf("One", "Two", "Three", "Four")
    println(numbersString)
    println(numbersString.joinToString())

    val listString = StringBuffer("The list of numbers")
    println(numbersString.joinTo(listString))

    println(numbersString.joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))

    val numbers = (1..100).toList()
    println(numbers.joinToString(limit = 25, truncated = "<...>"))

    println(numbersString.joinToString { "Element: ${it.uppercase()}" })


}