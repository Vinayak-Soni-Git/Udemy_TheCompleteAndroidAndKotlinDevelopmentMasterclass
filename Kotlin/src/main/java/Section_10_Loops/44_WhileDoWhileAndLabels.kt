package Section_10_Loops

fun main() {
    var number = 0
    while (number <= 10) {
        number++
        if (number == 7) {
            continue
        }
        println(number)
    }
}

