package Section_13_ObjectOrientedProgramming

fun main() {
    val listView: ListView = ListView(arrayOf("first", "second", "third", "fourth"))
    listView.ListViewItem().displayItem(2)
}

class ListView(val items: Array<String>) {
    inner class ListViewItem() {
        fun displayItem(position: Int) {
            println(items[position])
        }
    }
}