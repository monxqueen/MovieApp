package com.monique.projetointegrador.domain.model


data class Movie(
    val imgHome: String? = null,
    val id: Int,
    val title: String? = null,
    val rating: Float,
    val genreIds: List<Int>,
    var isFavorite: Boolean = false,
) {

    fun getRating(): String{
        val rating = (rating*10).toInt()
        return "$rating%"
    }
}

