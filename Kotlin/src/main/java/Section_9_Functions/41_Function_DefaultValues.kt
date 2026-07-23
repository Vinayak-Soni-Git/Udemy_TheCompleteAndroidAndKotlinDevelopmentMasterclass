package Section_9_Functions

fun main() {
    sendMessage("Alexa", "Hey there")
    sendMessage("Alexa")
    sendMessage()
    sendMessage(message = "Hey everyone")
}

fun sendMessage(name: String = "User", message: String = "") {
    println("Name = $name, Message = $message")
}