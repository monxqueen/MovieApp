package com.monique.projetointegrador.data.repository

import com.monique.projetointegrador.data.model.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun getService(): TMDBService {

        //criando uma instância do logging interceptor pra poder me mostrar no logcat tudo que vier do body da aplicação
        //precisa baixar a bibliotecado logging interceptor la no build.gradle
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        //okhttp me ajuda a enfiar algo no meu request através do addInterceptor, nesse caso estou inserindo a minha api key.
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logging)
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val urlWithKey = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constants.PRIVATE_KEY.value)
                .build()

            chain.proceed(original.newBuilder().url(urlWithKey).build())
        }

        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.value)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()

        return api.create(TMDBService::class.java)
    }

}