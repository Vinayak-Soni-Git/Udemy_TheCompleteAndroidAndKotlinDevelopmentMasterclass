package Section_13_ObjectOrientedProgramming

fun main(args: Array<String>) {
    val name1 = "Alex"
    val name2 = "Alex"

    println(name1 == name2)
    println(name1 === name2)

    val user1 = UserType2("Alex", "Double", 23)
    val user2 = UserType2("Alex", "Double", 23)

    println(user1 === user2)
    println(user1.equals(user2))

    println(user1)
    println(user2)


    val userDataType = UserDataType("Vinayak", "Soni", 24)
    val userDataType2 = UserDataType("Garima", "Sain", 22)
    println(userDataType)
    println(userDataType == userDataType2)
}

class UserType2(var firstName: String, var lastName: String, var age: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other is UserType2) {
            return this.firstName == other.firstName
                    && this.lastName == other.lastName
                    && this.age == other.age
        }
        return false
    }

    override fun hashCode(): Int {
        return 0
    }

    override fun toString(): String {
        return ("UserType2(firstName='$firstName', lastName='$lastName', age=$age)")
    }
}

data class UserDataType(var firstName: String, var lastName: String, var age: Int)