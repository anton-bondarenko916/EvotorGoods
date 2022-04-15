package com.example.evotor.goods.entity

import com.google.gson.annotations.SerializedName

data class ApiItems(
    @SerializedName("items")
    val items: List<ApiGood>
)