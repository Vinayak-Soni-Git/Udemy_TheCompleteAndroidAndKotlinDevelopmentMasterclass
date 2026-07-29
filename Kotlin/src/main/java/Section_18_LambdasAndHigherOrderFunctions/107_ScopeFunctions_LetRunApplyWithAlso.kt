package Section_18_LambdasAndHigherOrderFunctions

fun main() {
    val user = User().apply {
        this.firstName = "Alex"
        this.lastName = "Dobinca"
        this.age = 24
    }
    user.firstName = "Alex"
    user.lastName = "Dobinca"
    user.age = 24

    with(user) {
        firstName = "Alex"
        lastName = "Dobinca"
        age = 24
    }

    User2("Alex", "Dobinca", 23).also {
        println(it)
    }

    val text: String? = null
    text?.let {
        println(it)
    }
}

class User() {
    var firstName = ""
    var lastName = ""
    var age = 0
}

class User2(var firstName: String, var lastName: String, var age: Int)
