package com.example.firstkotlinapp.base

import android.app.Application
import com.example.firstkotlinapp.koin.koinmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class baseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@baseApplication)
            modules(koinmodelModule)
        }
    }
}