package com.example.evotor.goods.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.evotor.goods.database.GoodDatabase
import com.example.evotor.goods.entity.Good
import com.example.evotor.goods.repository.Repository
import com.example.evotor.goods.utils.Event
import kotlinx.coroutines.launch

class GoodsListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    private val mutableGoodsResponse: MutableLiveData<Event<List<Good>>> = MutableLiveData()
    fun observeGoodsResponse(): LiveData<Event<List<Good>>> = mutableGoodsResponse

    init {
        val goodDao = GoodDatabase.getDatabase(application).goodDao()
        repository = Repository(goodDao)
    }

    fun getGoods(shopUUID: String) {
        viewModelScope.launch {
            try {
                mutableGoodsResponse.value = Event.loading()
                val goods: List<Good> = repository.getGoods(shopUUID)
                mutableGoodsResponse.value = Event.success(goods)
            } catch (exception: Exception) {
                mutableGoodsResponse.value = Event.error(exception)
            }
        }
    }
}