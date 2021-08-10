package com.monique.projetointegrador.domain

class MovieDetail(
    val backdrop_path: String? = null,
    val genres: List<Genre>,
    val id: Int,
    val overview: String? = null,
    val release_date: String,
    val runtime: Int? = null,
    val vote_average: Float,
    val title: String,
    var isFavorite: Boolean = false,
) {
    fun getRating(): String{
        val rating = (vote_average*10).toInt()
        return "$rating%"
    }

    fun getReleaseYear(): String {
        return release_date.take(4)
    }

    fun getRuntime(): String {
        runtime?.let{
            return if(runtime < 60){
                runtime.toString() + "min"
            }else{
                val time = runtime/60
                val hours = time.toString().substringBefore(".").toInt()
                val minutes = runtime - (hours * 60)
                hours.toString() +
                        "h " +
                        minutes.toString() +
                        "min"
            }
        }
        return ""
    }
}

