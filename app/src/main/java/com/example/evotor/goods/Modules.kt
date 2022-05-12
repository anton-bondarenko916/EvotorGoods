package com.example.evotor.goods

import com.example.evotor.goods.api.Api
import com.example.evotor.goods.database.GoodDatabase
import com.example.evotor.goods.repository.EvotorRepository
import com.example.evotor.goods.repository.SharedPreferencesRepository
import com.example.evotor.goods.ui.goods.goods.GoodsListViewModel
import com.example.evotor.goods.ui.goods.settings.SettingsViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", Constants.TOKEN)
                    .build()
                chain.proceed(newRequest)
            }.build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(Api::class.java)
    }
    //TODO ПРоверить, что все ок
}

val dataBaseModule = module {
    single { GoodDatabase.getDatabase(get()) }
}

val repositoryModule = module {
    single { EvotorRepository(get(), get()) }
    single { SharedPreferencesRepository(get()) }
}

val viewModelModules = module {
    viewModel { GoodsListViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}

fun getModules(): List<Module> {
    return listOf(networkModule, dataBaseModule, repositoryModule, viewModelModules)
}

