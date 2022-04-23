package com.example.evotor.goods.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<RoomGood>)

    @Query("SELECT * FROM items_table")
    suspend fun getItems(): List<RoomGood>
}