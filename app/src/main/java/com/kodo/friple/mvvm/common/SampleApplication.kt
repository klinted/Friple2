package com.kodo.friple.mvvm.common

import android.app.Application
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImageTranscoderType
import com.facebook.imagepipeline.core.MemoryChunkType
import com.kodo.friple.dagger.components.AppComponent
import com.kodo.friple.dagger.components.DaggerAppComponent

class SampleApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
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