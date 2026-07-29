package Section_13_ObjectOrientedProgramming

fun main() {
    val view = View()
    val button = Button("Login", "Center")
    val roundedButton = RoundButton("Sign Up", "Center", 40)

    view.draw()
    button.draw()
    roundedButton.draw()
}

open class View() {
    open fun draw() {
        println("Drawing the view...")
    }
}

open class Button(val text: String, val orientation: String) : View() {
    override fun draw() {
        println("drawing the button...")
        super.draw()
    }
}

class RoundButton(text: String, orientation: String, val corners: Int) : Button(text, orientation) {
    override fun draw() {
        println("drawing rounded button...")
        super.draw()
    }
}