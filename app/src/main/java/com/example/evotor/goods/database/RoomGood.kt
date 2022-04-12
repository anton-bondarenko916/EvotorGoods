package com.example.evotor.goods.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.evotor.goods.entity.ApiGood
import java.math.BigDecimal

@Entity(
    tableName = "items_table",
    indices = [Index(value = ["id"], unique = true)])

data class RoomGood(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "quantity")
    val quantity: Double,
    @ColumnInfo(name = "measure_name")
    val measureName: String,
    @ColumnInfo(name = "allow_to_sell")
    val allowToSell: Int
)

fun RoomGood.toApiGood() = ApiGood(
    id = this.id,
    name = this.name,
    price = BigDecimal(this.price),
    quantity = BigDecimal(this.quantity),
    measureName = this.measureName,
    allowToSell = this.allowToSell == 1
)