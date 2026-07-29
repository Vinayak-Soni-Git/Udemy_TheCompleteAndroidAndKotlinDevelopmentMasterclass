package Section_14_Collections

fun main() {
    val numbers = listOf("One", "Two", "Three", "Four")
    val longerThan3 = numbers.filter { it.length > 4 }
    println(longerThan3)

    val numbersMap = mapOf("Key 1" to 1, "Key 2" to 2, "Key 3" to 3, "Key 101" to 101)
    val filterMap = numbersMap.filter { it.key.endsWith("1") && it.value > 100 }

    val filterIndex = numbers.filterIndexed { index, value -> (index != 0) && (value.length < 5) }
    val filteredNot = numbers.filterNot { it.length <= 3 }

    val mixedList = listOf(1, 2, 3, "A", "B", "C", "Hello World", "Alex", false)
    mixedList.filterIsInstance<Char>().forEach { println(it) }
    mixedList.filterIsInstance<Int>().forEach { println(it) }

    val (match, rest) = numbers.partition { it.length > 3 }
    println(match)
    println(rest)
}