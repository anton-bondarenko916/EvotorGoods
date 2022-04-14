package com.example.evotor.goods.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.example.evotor.goods.database.GoodDatabase
import com.example.evotor.goods.database.RoomGood
import com.example.evotor.goods.database.toApiGood
import com.example.evotor.goods.entity.ApiItems
import com.example.evotor.goods.entity.toRoomGood
import com.example.evotor.goods.repository.Repository
import com.example.evotor.goods.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class GoodsListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    private val mutableGoodsResponse: MutableLiveData<Event<ApiItems>> = MutableLiveData()
    fun observeGoodsResponse(): LiveData<Event<ApiItems>> = mutableGoodsResponse

    init {
        val goodDao = GoodDatabase.getDatabase(application).goodDao()
        repository = Repository(goodDao)
    }

    fun getGoods(shopUUID: String) {
        viewModelScope.launch {
            try {
                mutableGoodsResponse.value = Event.loading()
                val goods: ApiItems = repository.getGoods(shopUUID)
                mutableGoodsResponse.value = Event.success(goods)
            } catch (exception: Exception) {
                mutableGoodsResponse.value = Event.error(exception)
            }
        }
    }
}