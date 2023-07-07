package com.monique.projetointegrador.data.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private fun getLoggingInterceptor(): OkHttpClient.Builder {
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
        return okHttpClient
    }

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.value)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getLoggingInterceptor().build())
            .build()
    }
}