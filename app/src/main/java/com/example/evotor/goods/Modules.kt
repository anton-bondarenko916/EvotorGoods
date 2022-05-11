package com.example.evotor.goods

import com.example.evotor.goods.api.RetrofitInstance
import com.example.evotor.goods.api.provideApi
import com.example.evotor.goods.database.GoodDatabase
import com.example.evotor.goods.repository.EvotorRepository
import com.example.evotor.goods.repository.SharedPreferencesRepository
import com.example.evotor.goods.ui.goods.goods.GoodsListViewModel
import com.example.evotor.goods.ui.goods.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitInstance() }
    single { provideApi(get()) }
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
    return listOf(networkModule, repositoryModule, viewModelModules)
}

