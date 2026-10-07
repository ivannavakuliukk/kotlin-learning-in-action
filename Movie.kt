package kotlin_in_action

data class Movie(
    val title: String,
    val genre: String,
    val year: Int,
    val rating: Double
)

val movies = listOf(
    Movie("Inception", "Sci-Fi", 2010, 8.8),
    Movie("Interstellar", "Sci-Fi", 2014, 8.7),
    Movie("The Dark Knight", "Action", 2008, 9.0),
    Movie("Gladiator", "Action", 2000, 8.5),
    Movie("The Green Mile", "Drama", 1999, 8.6),
    Movie("Forrest Gump", "Drama", 1994, 8.8),
    Movie("Dune", "Sci-Fi", 2021, 8.0),
    Movie("Parasite", "Drama", 2019, 8.5)
)
