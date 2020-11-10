package com.kodo.friple.mvvm.common

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.kodo.friple.mvvm.view.containers.TabContainerFragment
import com.kodo.friple.mvvm.view.fragments.*

object Screens {

    fun Tab(tabName: String) = FragmentScreen("Tab") {
        TabContainerFragment.getNewInstance(tabName)
    }

    fun Home(containerName: String, number: Int) = FragmentScreen("Home") {
        HomeView.getNewInstance(containerName, number)
    }

    fun Chats(containerName: String, number: Int) = FragmentScreen("Chats") {
        ChatsView.getNewInstance(containerName, number)
    }

    fun LogProfile(containerName: String, number: Int) = FragmentScreen("LogProfile") {
        LogProfileView.getNewInstance(containerName, number)
    }

    fun Profile(containerName: String, number: Int) = FragmentScreen("Profile") {
        ProfileView.getNewInstance(containerName, number)
    }

    fun Log(containerName: String, number: Int) = FragmentScreen("Log") {
        LogView.getNewInstance(containerName, number)
    }

    fun Reg(containerName: String, number: Int) = FragmentScreen("Log") {
        RegView.getNewInstance(containerName, number)
    }
}