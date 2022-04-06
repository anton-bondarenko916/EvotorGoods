package com.example.evotor.goods.entity

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Good(
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
