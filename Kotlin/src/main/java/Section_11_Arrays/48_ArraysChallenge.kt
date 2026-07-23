package Section_11_Arrays

fun main() {
    val max = findMax(arrayOf(4, 6, 7, 4, 3, 6))
    val min = findMin(arrayOf(4, 7, 9, 20, 7, 100))
    println("Max = $max")
    println("Min = $min")

    val max2 = findMinMax(arrayOf(20, 40, 50, 60, 100), true)
    val min2 = findMinMax(arrayOf(20, 40, 50, 60, 100), false)
    println("Max2 = $max2")
    println("Min2 = $min2")
}

fun findMax(numbers: Array<Int>): Int {
    var max = numbers[0]
    for (number in numbers) {
        if (number > max) {
            max = number
        }
    }
    return max
}

fun findMin(numbers: Array<Int>): Int {
    var min = numbers[0]
    for (number in numbers) {
        if (number < min) {
            min = number
        }
    }
    return min
}

fun findMinMax(numbers: Array<Int>, searchMax: Boolean): Int {
    var max = numbers[0]
    var min = max
    if (searchMax) {
        for (number in numbers) {
            if (number > max) {
                max = number
            }
        }
        return max
    } else {
        for (number in numbers) {
            if (number < max) {
                min = number
            }
        }
        return min
    }
}