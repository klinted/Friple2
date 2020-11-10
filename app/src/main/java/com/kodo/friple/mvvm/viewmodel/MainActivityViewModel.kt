package com.kodo.friple.mvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

class MainActivityViewModel(val router: Router): BaseViewModel(){

    fun onBackPressed() {
        router.exit()
    }
}