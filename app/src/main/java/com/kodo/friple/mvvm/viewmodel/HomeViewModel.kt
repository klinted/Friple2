package com.kodo.friple.mvvm.viewmodel

import com.github.terrakok.cicerone.Router

class HomeViewModel(val router: Router) : BaseViewModel() {


    fun onBackPressed() {
        router.exit()
    }
}