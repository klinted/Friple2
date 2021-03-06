package com.kodo.friple.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

class ChatsViewModel(val router: Router): BaseViewModel() {

    fun onBackPressed() {
        router.exit()
    }
}