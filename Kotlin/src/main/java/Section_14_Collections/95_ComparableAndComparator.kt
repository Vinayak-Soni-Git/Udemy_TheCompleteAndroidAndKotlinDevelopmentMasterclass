package Section_14_Collections

fun main() {
    val numbers = mutableListOf(2, 5, 1, 40, 20, 100, 60)
    numbers.sorted().forEach { println(it) }

    val laptops = mutableListOf(
        Laptop("Dell", 600, 16),
        Laptop("Dell", 600, 16),
        Laptop("Dell", 600, 16)
    )
    laptops.sorted().forEach { println(it) }
    laptops.sortedWith(ComparatorRam()).forEach { println(it) }

    laptops.sortedBy { it.price }.forEach { println(it) }
    laptops.sortedBy { it.ram }.forEach { println(it) }
}

data class Laptop(val brand: String, val price: Int, val ram: Int) : Comparable<Laptop> {
    override fun compareTo(other: Laptop): Int {
        return if (this.price > other.price) {
            1
        } else if (this.price < other.price) {
            -1
        } else {
            0
        }
    }
}

class ComparatorRam : Comparator<Laptop> {
    override fun compare(p0: Laptop, p1: Laptop): Int {
        return if (p0.ram > p1.ram) {
            1
        } else if (p0.ram < p1.ram) {
            -1
        } else {
            0
        }
    }
}
