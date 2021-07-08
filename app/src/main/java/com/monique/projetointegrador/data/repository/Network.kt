package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.model.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL.value)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}