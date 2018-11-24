package com.example.musicsample.module

import com.example.musicsample.repository.ItuneRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module.module

val networkModule = module {
    single { OkHttpClient() }
    single { Gson() }
    single { ItuneRepository(get(), get()) }
}