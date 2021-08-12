package com.monique.projetointegrador.data.model.genres

import com.google.gson.annotations.SerializedName

data class GenresListResponse(
    @SerializedName("genres")
    val genres: List<GenreResponse>
)
