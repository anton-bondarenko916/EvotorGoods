package com.example.evotor.goods.repository

import com.example.evotor.goods.api.RetrofitInstance
import com.example.evotor.goods.entity.Items
import retrofit2.Response

class Repository {
    suspend fun getGoods(shopUUID: String, token: String): Response<Items> {
        return RetrofitInstance.api.getGoods(shopUUID, token)
    }
}