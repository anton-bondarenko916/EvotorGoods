package com.example.evotor.goods.repository

import android.content.Context
import com.example.evotor.goods.Constants

//TODO В Koin
class SharedPreferencesRepository(context: Context) {

    private val settings = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

    fun saveStyle(style: String) {
        settings.edit().putString(Constants.STYLE, style).apply()
    }

    fun getStyle() = settings.getString(Constants.STYLE, Constants.LIST_STYLE) ?: Constants.LIST_STYLE
}