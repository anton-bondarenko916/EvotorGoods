package com.example.evotor.goods.entity

import java.math.BigDecimal

data class Good(
    val name: String,
    val price: BigDecimal,
    val quantity: BigDecimal,
    val measureName: String,
    val allowToSell: Boolean
)