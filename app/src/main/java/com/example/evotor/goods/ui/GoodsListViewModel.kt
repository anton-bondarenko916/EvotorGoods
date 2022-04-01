package com.example.evotor.goods.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evotor.goods.entity.Items
import com.example.evotor.goods.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class GoodsListViewModel(private val repository: Repository): ViewModel() {

    private val mutableGoodsResponse: MutableLiveData<Response<Items>> = MutableLiveData()
    var goodsResponse: LiveData<Response<Items>> = mutableGoodsResponse

    fun getGoods(shopUUID: String, token: String) {
        viewModelScope.launch {
            mutableGoodsResponse.value = repository.getGoods(shopUUID, token)
            goodsResponse = mutableGoodsResponse
        }
    }
}