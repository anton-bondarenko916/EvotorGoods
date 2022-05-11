package com.example.evotor.goods.api

import com.example.evotor.goods.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", Constants.TOKEN)
                    .build()
                chain.proceed(newRequest)
            }.build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}