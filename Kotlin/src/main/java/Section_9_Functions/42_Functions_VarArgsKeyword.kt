package Section_9_Functions

fun main() {
    val sum = sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(sum)
}

fun sum(vararg numbers: Int): Int {
    var result = 0
    for (number in numbers) {
        result += number
    }
    return result
}

fun display(vararg numbers: Int) {
    numbers.forEach { println(it) }
}