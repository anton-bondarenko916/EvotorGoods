package com.example.evotor.goods.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.evotor.goods.entity.ApiGood
import com.example.evotor.goods.entity.Good
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
    val price: BigDecimal,
    @ColumnInfo(name = "quantity")
    val quantity: BigDecimal?,
    @ColumnInfo(name = "measure_name")
    val measureName: String,
    @ColumnInfo(name = "allow_to_sell")
    val allowToSell: Boolean
)

fun RoomGood.toGood() = Good(
    name = this.name,
    price = this.price,
    quantity = this.quantity ?: BigDecimal.ZERO,
    measureName = this.measureName,
    allowToSell = this.allowToSell
)