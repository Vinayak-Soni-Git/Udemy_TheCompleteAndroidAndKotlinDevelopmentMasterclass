package Section_13_ObjectOrientedProgramming

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val user = U()
    with(user) {
        firstName = "Alex"
        lastName = "Dobinca"
    }
    with(user) {
        println(firstName)
        println(lastName)
    }
}

//interface A {
//    fun print()
//}
//
//interface B {
//    fun print2()
//}
//
//open class FirstDelegate : A {
//    override fun print() {
//
//    }
//}
//
//open class SecondDelegate : B {
//    override fun print2() {
//
//    }
//}

class U {
    var firstName by FormatDelegate()
    var lastName by FormatDelegate()

}

class FormatDelegate : ReadWriteProperty<Any?, String> {
    private var formattedString: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return formattedString
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        formattedString = value.lowercase()
    }
}