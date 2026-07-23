package Section_13_ObjectOrientedProgramming

fun main() {
    val databaseInstance = Database.getInstance()
    val instance = Database.getInstance()
    println(databaseInstance)
    println(instance)
}

class Database private constructor() {
    companion object {
        private var instance: Database? = null

        fun getInstance(): Database? {
            if (instance == null) {
                instance = Database()
            }
            return instance
        }
    }
}

object Databases {
    init {
        println("Database created")
    }
}

