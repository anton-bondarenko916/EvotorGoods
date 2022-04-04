package com.example.evotor.goods.repository

import com.example.evotor.goods.api.RetrofitInstance
import com.example.evotor.goods.entity.Items
import retrofit2.Response
import java.lang.RuntimeException

class Repository {
    suspend fun getGoods(shopUUID: String, token: String): Items {
        //TODO Проверка функции на isSuccessful. Если true, то возвращаем значение Items,
        // Если false, то возвращаем throw RuntimeException("server error)
        val response = RetrofitInstance.api.getGoods(shopUUID, token)
        if (response.isSuccessful) {
            return response.body() ?: throw RuntimeException("goods is null")
        } else {
            throw RuntimeException("error when get goods")
        }
    }
}