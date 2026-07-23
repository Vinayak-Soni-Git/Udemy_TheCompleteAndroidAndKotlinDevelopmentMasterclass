package Section_9_Functions

fun main(args: Array<String>) {
    val max = getMax(5, 10)
    val max2 = getMax(5.0, 10.0)
    val max3 = getMax(5, 10, 15)
}

fun getMax(a: Double, b: Double) = if (a > b) a else b

fun getMax(a: Int, b: Int, c: Int) = if (a >= b && a >= c) a else if (b >= a && b >= c) b else c
