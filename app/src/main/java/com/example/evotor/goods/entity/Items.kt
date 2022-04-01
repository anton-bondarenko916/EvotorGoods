package com.example.evotor.goods.entity

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("items")
    val items: ArrayList<Good>
)