package Section_13_ObjectOrientedProgramming

fun main() {
    val clickListener = ClickListener()
    val loginButton = ButtonType2("Login", 1232, object : OnClickListener {
        override fun onClick() {
            super.onClick()
        }
    })

    val signUpButton = ButtonType2("Sign Up", 23423, object : OnClickListener {
        override fun onClick() {
            super.onClick()
        }
    })
}

class ButtonType2(val text: String, val id: Int, onClickListener: OnClickListener)

class ClickListener : OnClickListener {
    override fun onClick() {

    }
}

interface OnClickListener {
    fun onClick() {

    }
}