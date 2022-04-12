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

class GoodsListViewModel(
    application: Application)
    : AndroidViewModel(application) {

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
                val goods: ApiItems
                if (isInternetAvailable(getApplication())) {
                    goods = repository.getGoods(shopUUID)
                    updateDatabase(goods.items.map { it.toRoomGood() })
                } else {
                    val items = repository.getGoodsFromDatabase()
                    if (items.isNotEmpty()) {
                        goods = ApiItems(items.map { it.toApiGood() })
                    } else {
                        throw RuntimeException("Database is empty!")
                    }
                }
                mutableGoodsResponse.value = Event.success(goods)
            } catch (exception: Exception) {
                mutableGoodsResponse.value = Event.error(exception)
            }
        }
    }

    private suspend fun updateDatabase(items: List<RoomGood>) {
        repository.updateDataBase(items)
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}