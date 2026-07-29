package Section_14_Collections

fun main() {
    val numbers = mutableListOf("one", "two", "three", "four")
    val plusList = numbers + "five"
    println(plusList)

    val minusList = numbers - mutableListOf("three", "four")
    println(minusList)
}