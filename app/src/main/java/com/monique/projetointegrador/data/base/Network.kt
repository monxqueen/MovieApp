package com.monique.projetointegrador.data.base

import com.monique.projetointegrador.data.remotesource.MoviesRemoteSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun getMoviesRemoteSource(): MoviesRemoteSource {
        val api = createService()
        return api.create(MoviesRemoteSource::class.java)
    }

    private fun createService(): Retrofit {

        //criando uma instância do logging interceptor pra poder me mostrar no logcat tudo que vier do body da aplicação
        //precisa importar a biblioteca do logging interceptor la no build.gradle
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        //okhttp me ajuda a colocar algo no meu request (url) através do addInterceptor, nesse caso estou inserindo a minha api key.
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }
}