package Section_14_Collections

fun main(){
    val stringNumbers = listOf("one", "two", "three", "four", "five", "six")
    println(stringNumbers.elementAt(3))
    println(stringNumbers.first())
    println(stringNumbers.last())

    println(stringNumbers.first{it.length > 3})
    println(stringNumbers.last{it.startsWith("f")})
    println(stringNumbers.random())
}