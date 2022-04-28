package com.example.evotor.goods.api

import com.example.evotor.goods.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //TODO Реализовать с Koin
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", Constants.TOKEN)
                    .build()
                chain.proceed(newRequest)
            }.build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}

//val networkModule = module {
//    single<OAuthTokenApi> { OAuthTokenApi.provideTokenAPI() }
//    single<ReceiptApi> {
//        ReceiptApi.provideReceiptApi(
//            listInterceptors = provideListInterceptors(get()),
//            networkConnectionInterceptor = get()
//        )
//    }
//    single<MarketDeviceInfoApi> {
//        MarketDeviceInfoApi.provideMarketDeviceInfoApi(
//            provideListInterceptors(get()),
//            tokenInterceptor = get()
//        )
//    }
//    single<TokenApi> { TokenApi.provideTokenApi() }
//
//    single {
//        NetworkConnectionInterceptor(context = androidContext())
//    }
//    single {
//        TokenInterceptor(userInfoRepository = get(), tokenRepository = get())
//    }
//}