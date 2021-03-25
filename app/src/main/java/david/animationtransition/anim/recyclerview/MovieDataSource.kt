package david.animationtransition.anim.recyclerview

import kotlin.random.Random

val movies = listOf(
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 1", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 2", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 3", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 4", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 5", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 6", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 7", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 2", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 3", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 4", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 5", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 6", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 7", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 2", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 3", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 4", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 5", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 6", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 7", "", ""),
    MovieAdapterItem.Movie(1, Random.nextInt(10, 100) / 10f, "whatever 8", "", "")
)

sealed class MovieAdapterItem(open val id: Int) {

    object PlaceHolder : MovieAdapterItem(-1)

    data class Movie(
        override val id: Int,
        val vote: Float,
        val title: String,
        val image: String,
        val overview: String
    ) : MovieAdapterItem(id)
}
