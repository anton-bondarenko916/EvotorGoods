package com.example.evotor.goods.api

import com.example.evotor.goods.entity.ApiItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

fun provideApi(retrofitInstance: RetrofitInstance): Api {
    return retrofitInstance.retrofit.create(Api::class.java)
}

interface Api {
    @GET("stores/{shop-uuid}/products")
    suspend fun getGoods(@Path("shop-uuid") shopUUID: String): Response<ApiItems>
}