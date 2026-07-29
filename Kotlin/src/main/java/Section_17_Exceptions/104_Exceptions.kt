package Section_17_Exceptions

fun main() {
    val a = 5
    val b = 0

    try {
        println(a / b)
    } catch (e: ArithmeticException) {
        println("You can't divide by zero ${e.message}")
    } finally {
        println("Divide by zero")
    }
}