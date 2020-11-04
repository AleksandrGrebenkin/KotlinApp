package com.github.aleksandrgrebenkin.kotlinapp.view

import android.app.Application
import com.github.aleksandrgrebenkin.kotlinapp.di.appModule
import com.github.aleksandrgrebenkin.kotlinapp.di.mainModule
import com.github.aleksandrgrebenkin.kotlinapp.di.noteModule
import com.github.aleksandrgrebenkin.kotlinapp.di.splashModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    companion object {
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}