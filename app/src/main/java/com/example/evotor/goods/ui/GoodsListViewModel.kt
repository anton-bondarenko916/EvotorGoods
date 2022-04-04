package com.example.evotor.goods.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evotor.goods.entity.Items
import com.example.evotor.goods.repository.Repository
import com.example.evotor.goods.utils.Event
import com.example.evotor.goods.utils.Status
import kotlinx.coroutines.launch
import retrofit2.Response

class GoodsListViewModel(private val repository: Repository) : ViewModel() {

    private val mutableGoodsResponse: MutableLiveData<Event<Items>> = MutableLiveData()

    //TODO fun observeGoodsResponse(): LiveData<Response<Items>> = mutableGoodsResponse
    fun observeGoodsResponse(): LiveData<Event<Items>> = mutableGoodsResponse

    fun getGoods(shopUUID: String, token: String) {
        viewModelScope.launch {
            //TODO try/catch
            try {
                mutableGoodsResponse.value = Event.loading()
                val goods = repository.getGoods(shopUUID, token)
                mutableGoodsResponse.value = Event.success(goods)
            } catch (exception: Exception) {
                mutableGoodsResponse.value = Event.error(exception)
            }


//            mutableGoodsResponse.value = repository.getGoods(shopUUID, token)
//            goodsResponse = mutableGoodsResponse //TODO Убрать
        }
    }
}