package Section_14_Collections

fun main() {
    val names = listOf("Name1", "Name2", "Name3")
    val mutableName = mutableListOf("Name1", "Name2", "Name3")

    val mutableSet = mutableSetOf("Name1", "Name2", "Name3", "Name1")

    mutableSet.forEach {
        println(it)
    }
    // names.add("Name4") will throw error because of immutability

    val users = mutableMapOf<Int, String>(1 to "Maria", 2 to "Alex", 3 to "John")
    users[3] = "Vled"
    users.remove(2)
    users.forEach { id, name ->
        println("$id, $name")
    }
}