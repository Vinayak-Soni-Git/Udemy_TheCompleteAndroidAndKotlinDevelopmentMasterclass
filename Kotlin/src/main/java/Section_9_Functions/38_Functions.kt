package Section_9_Functions

fun main() {
    sayHello("Alex", 22)

    val hasInternetConnection = true
    if (hasInternetConnection) {
        getData("Some data")
    } else {
        showMessage()
    }
}

fun sayHello(name: String, age: Int) {
    val number = age
    println("Hello $name, your age is $age")
}

fun getData(data: String) {
    println("Your data is : $data")
}

fun showMessage() {
    println("No internet connection")
}