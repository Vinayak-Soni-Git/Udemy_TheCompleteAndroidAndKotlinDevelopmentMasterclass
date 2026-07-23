package Section_13_ObjectOrientedProgramming

fun main() {
    val person1 = Person("Alex", "Rogers", 23)
    val person2 by lazy { Person("Person2", "LastName", 38) }

    println(person2.firstName)
}

class Person(var firstName: String, var lastName: String, var age: Int) {
    init {
        println("Person $firstName was created")
    }
}