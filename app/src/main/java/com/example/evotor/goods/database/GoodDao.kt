package com.example.evotor.goods.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GoodDao {

    //TODO OnConflictStrategy.REPLACE - возможно будет правильнее
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItems(items: List<RoomGood>)

    @Query("SELECT * FROM items_table")
    suspend fun getItems(): List<RoomGood>
}