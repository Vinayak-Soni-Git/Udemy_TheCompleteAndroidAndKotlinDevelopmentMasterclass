package Section_13_ObjectOrientedProgramming

//fun main(args: Array<String>) {
//    val car1 = Car("Tesla", "S Plaid", "Red", 4)
////    car1.name = "Tesla"
////    car1.model = "S Plaid"
////    car1.color = "Red"
////    car1.doors = 4
//
//    println("Name = ${car1.name}")
//    println("Model = ${car1.model}")
//    println("Color = ${car1.color}")
//    println("Doors = ${car1.doors}")
//
//    car1.move()
//    car1.stop()
//
//    println()
//
//    val car2 = Car("Ford", "Mustang", "Blue", 2)
////    car2.name = "Ford"
////    car2.model = "Mustang"
////    car2.color = "Blue"
////    car2.doors = 2
//
//    println("Name = ${car2.name}")
//    println("Model = ${car2.model}")
//    println("Color = ${car2.color}")
//    println("Doors = ${car2.doors}")
//
//    car2.move()
//    car2.stop()
//
//    val user = User("Alex", "Dobinca", 23)
//    val friend = User("John", "Smith", 30)
//
//    val user2 = User2("Alex")
//    val user3 = User2("Alex", "Smith")
//
//    // Named arguments
//    val user4 = User2(firstName = "Ioana", age = 19, lastName = "Rogers")
//
//    val userType3 = UserType3("John", "Wick", 38)
//    println(userType3.firstName)
//    println(userType3.lastName)
//
//    userType3.firstName = "Bruce"
//    userType3.lastName = "Wayne"
//}
//
////class Car(name: String, model: String, color: String, doors: Int) {
//////    var name = ""
//////    var model = ""
//////    var color = ""
//////    var doors = 0
////
////    fun move() {
////        println("The car $name is moving")
////    }
////
////    fun stop() {
////        println("The car $name is stopped")
////    }
////}
//
//// Primary Constructor: without val or var keyword these are just parameter not properties
//class Car(val name: String, val model: String, val color: String, val doors: Int) {
//    fun move() {
//        println("The car $name is moving")
//    }
//
//    fun stop() {
//        println("The car $name is stopped")
//    }
//}
//
//// 63.Initializer Block
//class User(name: String, var lastName: String, var age: Int) {
//    var name: String
//
//    init {
//        if (name.lowercase().startsWith("a")) {
//            this.name = name
//        } else {
//            this.name = "user"
//        }
//    }
//}
//
//// 64. Secondary Constructor
//
//class User2(var firstName: String, var lastName: String = "LastName", var age: Int) {
//    constructor(firstName: String) : this(firstName, "LastName", 0) {
//        println("first secondary constructor")
//    }
//
//    constructor(firstName: String, lastName: String) : this(firstName, lastName, 0) {
//        println("second secondary constructor")
//    }
//}
//
//// 66.Getters and Setters
//class UserType3(firstName: String, var lastName: String, var age: Int) {
//    var firstName = firstName
//        get() {
//            return "FirstName: $field"
//        }
//        set(value) {
//            println("$value was assigned to firstName property")
//            field = value
//        }
//
//    fun setFirstName(value:String){
//        this.firstName = value
//    }
//    fun getFirstName():String{
//        return this.firstName
//    }
//}