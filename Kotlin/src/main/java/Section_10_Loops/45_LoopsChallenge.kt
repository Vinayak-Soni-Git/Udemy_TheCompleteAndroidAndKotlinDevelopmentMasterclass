package Section_10_Loops

fun main() {
    var number = 1
    val lastNumber = 20
    var evenNumberCounter = 0

    while (number <= lastNumber) {
        number++
        if (!isEvenNumber(number)) {
            continue
        }
        evenNumberCounter++
        println(number)
    }
    println("Total even numbers: $evenNumberCounter")
}

fun isEvenNumber(number: Int): Boolean {
    if (number % 2 == 0) {
        return true
    } else {
        return false
    }
}