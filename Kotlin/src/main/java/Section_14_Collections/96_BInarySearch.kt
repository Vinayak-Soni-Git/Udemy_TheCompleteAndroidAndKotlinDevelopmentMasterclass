package Section_14_Collections

fun main() {
    val numbers = mutableListOf(1, 4, 1, 2, 3, 5, 9, 5, 10)
    println(binarySearch(numbers, 4))
}

private fun binarySearch(numbers: MutableList<Int>, element: Int): Int {
    var low = 0
    var high = numbers.size - 1

    while (low <= high) {
        val mid = (low + high) / 2
        val cmp = numbers[mid].compareTo(element)
        if (cmp < 0) {
            low = mid + 1
        } else if (cmp > 0) {
            low = mid - 1
        } else {
            return numbers[mid]
        }
    }
    return -1
}