package com.example.evotor.goods.repository

import com.example.evotor.goods.api.RetrofitInstance
import com.example.evotor.goods.database.GoodDao
import com.example.evotor.goods.database.toGood
import com.example.evotor.goods.entity.ApiItems
import com.example.evotor.goods.entity.Good
import com.example.evotor.goods.entity.toGood
import com.example.evotor.goods.entity.toRoomGood

class EvotorRepository(private val goodDao: GoodDao) {

    suspend fun getGoods(shopUUID: String): List<Good> {
        try {
            val response = RetrofitInstance.api.getGoods(shopUUID)
            if (response.isSuccessful) {
                goodDao.insertItems(response.body()?.items?.map { it.toRoomGood() } ?: listOf())
                return response.body()?.items?.map { it.toGood() } ?: throw RuntimeException("goods is null")
            } else {
                throw RuntimeException("some shit")
            }
        } catch (exception: Exception) {
            val itemsFromDatabase = goodDao.getItems().map { it.toGood() }
            if (itemsFromDatabase.isNotEmpty()) {
                return itemsFromDatabase
            } else {
                throw RuntimeException("error when get goods")
            }
        }
    }
}