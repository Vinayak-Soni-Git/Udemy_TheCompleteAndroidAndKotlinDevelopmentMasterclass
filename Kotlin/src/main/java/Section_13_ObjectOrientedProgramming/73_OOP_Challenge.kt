package Section_13_ObjectOrientedProgramming

fun main() {
    val alexAccount = Account("Alex")
    alexAccount.deposit(1000)
    alexAccount.withdraw(200)
    alexAccount.deposit(-20)
    alexAccount.withdraw(-100)

    val balance = alexAccount.calculateBalance()
    println("Balance is $balance")
}

class Account(val accountName: String) {
    private var balance = 0
    private var transactions = mutableListOf<Int>()

    fun deposit(amount: Int) {
        if (amount > 0) {
            transactions.add(amount)
            balance += amount
            println("$amount deposited, balance is ${this.balance}")
        } else {
            println("can not deposit negative sums")
        }
    }

    fun withdraw(withdrawAmount: Int) {
        if (withdrawAmount < 0) {
            transactions.add(-withdrawAmount)
            this.balance += -withdrawAmount
            println("$withdrawAmount withdrawn, balance is ${this.balance}")
        } else {
            println("can not withdraw negative sums")
        }
    }

    fun calculateBalance(): Int {
        this.balance = 0
        for (transaction in transactions) {
            this.balance += transaction
        }
        return this.balance
    }

}