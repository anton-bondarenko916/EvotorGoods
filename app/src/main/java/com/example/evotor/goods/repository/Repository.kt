package com.example.evotor.goods.repository

import com.example.evotor.goods.api.RetrofitInstance
import com.example.evotor.goods.database.GoodDao
import com.example.evotor.goods.database.RoomGood
import com.example.evotor.goods.database.toApiGood
import com.example.evotor.goods.entity.ApiItems
import java.lang.RuntimeException

class Repository(private val goodDao: GoodDao) {

    suspend fun getGoods(shopUUID: String): ApiItems {
        val response = RetrofitInstance.api.getGoods(shopUUID)
        return if (response.isSuccessful) {
            response.body() ?: throw RuntimeException("goods is null")
        } else {
            //request to db
            val itemsFromDatabase = getGoodsFromDatabase().map { it.toApiGood() }
            if (itemsFromDatabase.isNotEmpty()) {
                ApiItems(itemsFromDatabase)
            } else {
                throw RuntimeException("error when get goods")
            }
        }
    }

    suspend fun updateDataBase(items: List<RoomGood>) {
        goodDao.insertItems(items)
    }

    suspend fun getGoodsFromDatabase(): List<RoomGood> {
        return goodDao.getItems()
    }
}