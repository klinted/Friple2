package com.kodo.friple.mvvm.common

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.kodo.friple.dagger.components.NavigationComponent
import com.kodo.friple.dagger.components.DaggerNavigationComponent

class SampleApplication : Application() {

    val navigationComponent: NavigationComponent by lazy {
        DaggerNavigationComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Fresco.initialize(this)
    }

    companion object {
        lateinit var INSTANCE: SampleApplication
    }
}