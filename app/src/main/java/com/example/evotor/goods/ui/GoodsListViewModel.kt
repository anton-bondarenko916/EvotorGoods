package com.example.evotor.goods.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evotor.goods.entity.Items
import com.example.evotor.goods.repository.Repository
import com.example.evotor.goods.utils.Event
import kotlinx.coroutines.launch

class GoodsListViewModel(private val repository: Repository) : ViewModel() {

    private val mutableGoodsResponse: MutableLiveData<Event<Items>> = MutableLiveData()

    fun observeGoodsResponse(): LiveData<Event<Items>> = mutableGoodsResponse

    fun getGoods(shopUUID: String) {
        viewModelScope.launch {
            try {
                mutableGoodsResponse.value = Event.loading()
                val goods = repository.getGoods(shopUUID)
                mutableGoodsResponse.value = Event.success(goods)
            } catch (exception: Exception) {
                mutableGoodsResponse.value = Event.error(exception)
            }
        }
    }
}