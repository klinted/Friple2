package com.kodo.friple.dagger.components

import com.kodo.friple.dagger.modules.LocalNavigationModule
import com.kodo.friple.dagger.modules.NavigationModule
import com.kodo.friple.mvvm.view.activities.MainActivity
import com.kodo.friple.mvvm.view.containers.TabContainerFragment
import com.kodo.friple.mvvm.view.fragments.ChatsView
import com.kodo.friple.mvvm.view.fragments.HomeView
import com.kodo.friple.mvvm.view.fragments.LogProfileView
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NavigationModule::class,
    LocalNavigationModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: TabContainerFragment)

    fun inject(fragment: HomeView)

    fun inject(fragment: ChatsView)

    fun inject(fragment: LogProfileView)

}