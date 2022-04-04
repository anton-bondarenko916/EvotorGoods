package com.example.evotor.goods.entity

import com.google.gson.annotations.SerializedName

data class Good(
    @SerializedName("name")
    val name: String,
    //TODO BigDecimal
    @SerializedName("price")
    val price: Double,
    //TODO BigDecimal, если quantity не приходит, то подставляем 0
    @SerializedName("cost_price")
    val quantity: Double,
    @SerializedName("measure_name")
    val measureName: String,
    @SerializedName("allow_to_sell")
    val allowToSell: Boolean
)
