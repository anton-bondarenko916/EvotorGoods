package com.example.evotor.goods.ui.goods.goods

import android.app.Application
import androidx.lifecycle.*
import com.example.evotor.goods.database.GoodDatabase
import com.example.evotor.goods.entity.Good
import com.example.evotor.goods.repository.EvotorRepository
import com.example.evotor.goods.repository.SharedPreferencesRepository
import com.example.evotor.goods.utils.Event
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class GoodsListViewModel(application: Application) : AndroidViewModel(application) {

    private val evotorRepository: EvotorRepository
    private val sharedPreferencesRepository: SharedPreferencesRepository

    private val mutableGoodsResponse: MutableLiveData<Event<List<Good>>> = MutableLiveData()
    fun observeGoodsResponse(): LiveData<Event<List<Good>>> = mutableGoodsResponse

    init {
        val goodDao = GoodDatabase.getDatabase(application).goodDao()
        evotorRepository = EvotorRepository(goodDao)
        sharedPreferencesRepository = SharedPreferencesRepository(application)
    }

    fun getGoods(shopUUID: String) {
        viewModelScope.launch {
            try {
                mutableGoodsResponse.value = Event.loading()
                val goods: List<Good> = evotorRepository.getGoods(shopUUID)
                mutableGoodsResponse.value = Event.success(goods)
            } catch (exception: Exception) {
                mutableGoodsResponse.value = Event.error(exception)
            }
        }
    }

    fun getStyle(): String {
        try {
            return sharedPreferencesRepository.getStyle()!!
        } catch (exception: Exception) {
            throw RuntimeException("Error when get style")
        }
    }
}