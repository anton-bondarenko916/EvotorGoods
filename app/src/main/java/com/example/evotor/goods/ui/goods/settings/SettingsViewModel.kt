package com.example.evotor.goods.ui.goods.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.evotor.goods.repository.SharedPreferencesRepository
import java.lang.Exception
import java.lang.RuntimeException

class SettingsViewModel(application: Application): AndroidViewModel(application) {

    private val repository: SharedPreferencesRepository = SharedPreferencesRepository(application)

    fun saveStyle(style: String) {
        try {
            repository.saveStyle(style)
        } catch (exception: Exception) {
            throw RuntimeException("Error save in shared preferences")
        }
    }

    fun getStyle(): String {
        try {
            return repository.getStyle()!!
        } catch (exception: Exception) {
            throw RuntimeException("Error getting style")
        }
    }
}