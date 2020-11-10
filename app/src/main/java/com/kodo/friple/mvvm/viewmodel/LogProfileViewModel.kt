package com.kodo.friple.mvvm.viewmodel

import com.github.terrakok.cicerone.Router
import com.kodo.friple.mvvm.common.Screens.Log
import com.kodo.friple.mvvm.common.Screens.Reg

class LogProfileViewModel(val router: Router) : BaseViewModel() {


    fun toLogScreen(){
        router.navigateTo(Log("profile", 1))
    }

    fun toRegScreen(){
        router.navigateTo(Reg("profile", 1))
    }

    fun onBackPressed(){
        router.exit()
    }
}
