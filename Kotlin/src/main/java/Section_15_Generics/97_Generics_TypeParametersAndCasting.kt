package Section_15_Generics

//fun main() {
//    val footballPlayer = FootballPlayer("one")
//    val footballPlayer2 = FootballPlayer("two")
//
//    val baseballPlayer = BaseballPlayer("baseball player 1")
//    val baseballPlayer2 = BaseballPlayer("baseball player 2")
//
//    val team = Team<FootballPlayer>("Football Team", mutableListOf(footballPlayer, footballPlayer2))
//    team.addPlayer(footballPlayer2)
//
//    val baseballTeam = Team<BaseballPlayer>("Baseball team", mutableListOf(baseballPlayer))
//    baseballTeam.addPlayer(baseballPlayer2)
//
////    val footballTeam2 = Team<Player>("Football Team", mutableListOf<FootballPlayer>())
//
//    val gamesTeam = Team<CounterStrikePlayer>("Games Team", mutableListOf<GamesPlayer>())
//
//    val mixedList = mutableListOf(1, 2, 360, 'a', 'b', 'c', "Hello")
//    val specificList = getSpecificType<Int>(mixedList)
//}
//
//class Team<T>(
//    val name: String,
//    val players: MutableList<in T>
//) where T : Player, T : Listener {
//    fun addPlayer(player: T) {
//        if (players.contains(player)) {
//            println("Player: ${(player as Player).name} is already in the team")
//        } else {
//            players.add(player)
//            println("Player: ${(player as Player).name} is added to the team")
//        }
//    }
//}
//
//open class Player(val name: String) {
//
//}
//
//class FootballPlayer(name: String) : Player(name), Listener {
//    override fun listen() {
//
//    }
//}
//
//class BaseballPlayer(name: String) : Player(name)
//open class GamesPlayer(name: String) : Player(name)
//class CounterStrikePlayer(name: String) : GamesPlayer(name)
//
//inline fun <reified T> getSpecificType(list: List<Any>): List<T> {
//    val specificList = mutableListOf<T>()
//
//    for (element in list) {
//        if (element is T) {
//            specificList.add(element)
//        }
//    }
//    return specificList
//}
//
//interface Listener {
//    fun listen()
//}
//
//fun <T> addPlayer(player: T) where T : Player, T : Listener {
//
//}