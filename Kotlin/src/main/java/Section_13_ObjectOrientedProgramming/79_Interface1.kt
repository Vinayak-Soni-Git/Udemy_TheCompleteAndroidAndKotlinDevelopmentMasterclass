package Section_13_ObjectOrientedProgramming

fun main() {
    val dieselEngine = DieselEngine(440)
    dieselEngine.start()
}

interface Engine {
    fun start()
}

class DieselEngine(val horsePower: Int) : Engine {
    override fun start() {
        println("Diesel engine started")
    }
}

class PetrolEngine(val horsePower: Int) : Engine {
    override fun start() {
        println("Petrol engine started")
    }
}