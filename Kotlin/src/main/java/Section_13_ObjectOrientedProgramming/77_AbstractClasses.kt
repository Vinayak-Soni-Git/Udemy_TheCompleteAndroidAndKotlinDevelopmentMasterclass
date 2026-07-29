package Section_13_ObjectOrientedProgramming

fun main() {

}

abstract class AbstractVehicle {
    abstract fun move()
    abstract fun stop()
}

class CarType2 : AbstractVehicle() {
    override fun move() {}

    override fun stop() {}
}
