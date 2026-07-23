package Section_13_ObjectOrientedProgramming

fun main() {
    val result = Calculator.sum(5, 10)
    println(result)
    println(Calculator.max)
}

class Calculator {
    companion object {
        val max = 0
        fun sum(a: Int, b: Int): Int {
            return a + b
        }
    }
}

