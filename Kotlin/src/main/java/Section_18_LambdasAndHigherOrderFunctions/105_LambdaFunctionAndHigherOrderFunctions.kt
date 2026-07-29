package Section_18_LambdasAndHigherOrderFunctions

fun main() {
    val lam = { a: Int, b: Int -> println(a + b) }
    lam(10, 10)

    val sum = add(10, 10, lam)
    val sum2 = add(10, 20) { a: Int, b: Int ->
        println(a)
    }
}

fun add(a: Int, b: Int, action: (Int, Int) -> Unit) {
    println(a + b)
    action(a, b)
}