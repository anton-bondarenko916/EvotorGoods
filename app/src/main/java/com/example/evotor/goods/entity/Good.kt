package com.example.evotor.goods.entity

import com.google.gson.annotations.SerializedName

data class Good(
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("cost_price")
    val quantity: Double,
    @SerializedName("measure_name")
    val measureName: String,
    @SerializedName("allow_to_sell")
    val allowToSell: Boolean
)
