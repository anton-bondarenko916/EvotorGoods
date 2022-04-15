package com.example.evotor.goods.entity

import com.example.evotor.goods.database.RoomGood
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ApiGood(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: BigDecimal,
    @SerializedName("quantity")
    val quantity: BigDecimal?,
    @SerializedName("measure_name")
    val measureName: String,
    @SerializedName("allow_to_sell")
    val allowToSell: Boolean
)

fun ApiGood.toRoomGood() = RoomGood(
    id = this.id,
    name = this.name,
    price = this.price,
    quantity = this.quantity,
    measureName = this.measureName,
    allowToSell = this.allowToSell
)

fun ApiGood.toGood() = Good(
    name = this.name,
    price = this.price,
    quantity = this.quantity ?: BigDecimal.ZERO,
    measureName = this.measureName,
    allowToSell = this.allowToSell
)
