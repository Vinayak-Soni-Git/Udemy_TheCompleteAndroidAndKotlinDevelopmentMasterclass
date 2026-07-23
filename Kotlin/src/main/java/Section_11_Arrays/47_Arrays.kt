package Section_11_Arrays

fun main() {
    val names: Array<String> = arrayOf("John", "Stephen", "Megan")
    println(names.contentToString())

    println("First element = ${names[0]}")
    names[0] = "Alex"
    println("First element = ${names[0]}")
    println("Last Element = ${names[names.size - 1]}")

    val list = arrayOf(4, 5, 6, 7, 4, "Steven", "Bruce")

    for (name in names) {
        print("$name ")
    }
    for (element in list) {
        if (element is Int) {
            println(element)
        }
    }
}
