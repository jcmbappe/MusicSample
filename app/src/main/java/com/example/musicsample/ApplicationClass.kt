package com.example.musicsample

import android.app.Application
import com.example.musicsample.module.networkModule
import org.koin.android.ext.android.startKoin

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule))
    }
}