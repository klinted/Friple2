package com.kodo.friple.dagger.modules

import com.kodo.friple.mvvm.common.navigation.LocalCiceroneHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalNavigationModule {

    @Provides
    @Singleton
    fun provideLocalNavigationHolder(): LocalCiceroneHolder = LocalCiceroneHolder()
}