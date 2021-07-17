package com.monique.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class ResponseMovies(@SerializedName("results")
                          val movieResults: List<Movies>)
