package com.monique.projetointegrador.data.model.genres

import com.google.gson.annotations.SerializedName

data class ResponseGenres(
    @SerializedName("genres")
    val genres: List<GenreResponse>
)
