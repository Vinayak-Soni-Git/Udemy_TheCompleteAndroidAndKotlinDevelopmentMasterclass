package Section_13_ObjectOrientedProgramming

fun main() {
    val car = Car1("BMW", "Blue", 1, 4)
    val plane = Plane("Boeing", "White and Blue", 4, 10)

    car.move()
    car.stop()

    plane.move()

}

open class Vehicle(val name: String, val color: String) {
    open fun move() {
        println("$name is moving")
    }

    open fun stop() {
        println("$name has stopped")
    }
}

class Car1(name: String, color: String, val engines: Int, val doors: Int) : Vehicle(name, color) {
    override fun move() {
        super.move()
    }

}

class Plane(name: String, color: String, val engines: Int, val doors: Int) : Vehicle(name, color) {
    override fun move() {
        flying()
        super.move()
    }

    fun flying() {
        println("$name is flying")
    }
}