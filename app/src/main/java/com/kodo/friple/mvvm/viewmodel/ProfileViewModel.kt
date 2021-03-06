package com.kodo.friple.mvvm.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import com.github.terrakok.cicerone.Router
import com.kodo.friple.apputil.AppConfig
import com.kodo.friple.mvvm.common.Screens.LogProfile
import com.kodo.friple.mvvm.view.fragments.LogProfileView

class ProfileViewModel(val router: Router, val context: Context): BaseViewModel(){

    private val appConfig = AppConfig(context)

    // For display id of user (TEMPORARILY)
    val loginOfUser = ObservableField("Friple/${appConfig.getNameOfUser()}")

    // Logout from account. Here sets loginned status to false and opens new rootScreen "Log Profile"
    // for login or registration
    fun logOut(){
        appConfig.updateUserLoginStatus(false)
        router.newRootScreen(LogProfile("logProf", 0))
    }

    fun onBackPressed() {
        router.exit()
    }

}