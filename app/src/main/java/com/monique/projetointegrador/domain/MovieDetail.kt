package com.monique.projetointegrador.domain

class MovieDetail(
    val backdrop_path: String? = null,
    val genres: List<Genre>,
    val id: Int,
    val overview: String? = null,
    val release_date: String,
    val runtime: Int? = null,
    val vote_average: Float,
    val title: String
)