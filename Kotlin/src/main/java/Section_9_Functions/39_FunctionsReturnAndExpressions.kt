package Section_9_Functions

fun main(args: Array<String>) {
    val max = getMax(5, 10)
    val max2 = getMaxExpression(5, 10)
    println(max)
    println(max2)
}

fun getMaxExpression(a: Int, b: Int) = if (a > b) a else b

fun getMax(a: Int, b: Int): Int {
    val max = if (a > b) a else b
    return max
}
