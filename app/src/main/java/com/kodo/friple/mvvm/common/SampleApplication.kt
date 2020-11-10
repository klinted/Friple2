package com.kodo.friple.mvvm.common

import android.app.Application
import android.util.Log
import com.kodo.friple.dagger.components.AppComponent
import com.kodo.friple.dagger.components.DaggerAppComponent

class SampleApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: SampleApplication
    }
}