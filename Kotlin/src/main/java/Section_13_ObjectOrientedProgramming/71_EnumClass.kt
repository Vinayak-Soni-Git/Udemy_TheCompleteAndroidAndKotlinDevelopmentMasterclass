package Section_13_ObjectOrientedProgramming

fun main() {
    for (direction in Direction.entries) {
        println(direction)
    }
    println(Direction.NORTH.direction)
    println(Direction.NORTH.distance)
    println(Direction.NORTH.name)

    Direction.NORTH.printData()
}

enum class Direction(var direction: String, var distance: Int) {
    NORTH("north", 10),
    SOUTH("south", 20),
    EAST("east", 30),
    WEST("west", 40);

    fun printData() {
        println("Direction = $direction and distance = $distance")
    }
}