package com.example.evotor.goods.ui.goods.settings

import androidx.lifecycle.ViewModel
import com.example.evotor.goods.repository.SharedPreferencesRepository
import java.lang.Exception
import java.lang.RuntimeException

class SettingsViewModel(private val repository: SharedPreferencesRepository): ViewModel() {

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