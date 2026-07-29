package Section_14_Collections

fun main() {
    val colors = listOf("Red", "Brown", "Grey")
    val animals = listOf("Fox", "Bear", "Wolf")

    println(colors zip animals)
    println(colors.zip(animals) { color, animal ->
        "The ${animal.replaceFirstChar { it.uppercase() }} is $color"
    })

    val numberPairs = listOf("One" to 1, "Two" to 2, "Three" to 3, "Four" to 4)
    println(numberPairs)
    println(numberPairs.unzip())

    val nums = listOf("One", "Two", "Three", "Four")
    println(nums.associateWith { it.length })
    println(nums.associateBy { it.first().uppercase() })
    println(
        nums.associateBy(
            keySelector = { it.first().uppercase() },
            valueTransform = { it.length })
    )
}