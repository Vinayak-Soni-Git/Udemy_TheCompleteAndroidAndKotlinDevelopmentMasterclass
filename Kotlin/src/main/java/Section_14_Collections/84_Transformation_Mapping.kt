package Section_14_Collections

fun main() {
    val numbers = setOf(1, 2, 3, 4, 5)
    println(numbers.map { it * 10 })
    println(numbers.map { if (it == 2) it * 100 else it * 10 })
    println(numbers.mapIndexedNotNull { index, value -> if (index == 0) null else index * value })

    val numbersMap = mapOf("1" to 1, "2" to 2, "3" to 3)
    println(numbersMap.mapKeys { it.key.uppercase() })
    println(numbersMap.mapValues { it.value + it.key.length })

}