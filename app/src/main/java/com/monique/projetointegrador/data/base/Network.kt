package com.monique.projetointegrador.data.base

import com.monique.projetointegrador.data.remotesource.MoviesRemoteSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun getMoviesRemoteSource(): MoviesRemoteSource {
        val api = createService()
        return api.create(MoviesRemoteSource::class.java)
    }

    private fun createService(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logging)
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val urlWithKey = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constants.PRIVATE_KEY.value)
                .addQueryParameter("language", "pt-BR")
                .build()

            chain.proceed(original.newBuilder().url(urlWithKey).build())
        }

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.value)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }
}