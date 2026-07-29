package Section_18_LambdasAndHigherOrderFunctions

fun main() {
    upperCase("hello") {
        it.uppercase()
    }
}

fun upperCase(str: String, myFunction: (String) -> String) {
    val uppercaseWord = myFunction(str)
    println(uppercaseWord)
}