package Section_13_ObjectOrientedProgramming

fun main() {
    val movieUser = MovieUser("Tony", "Stark", 38)

    // println(movieUser.favoriteMovie) will throw error uninitialize property because of lateinit keyword
    movieUser.favoriteMovie = "Iron Man"
    println(movieUser.favoriteMovie)
}

class MovieUser(var firstName: String, var lastName: String, var age: Int) {
    lateinit var favoriteMovie: String
}