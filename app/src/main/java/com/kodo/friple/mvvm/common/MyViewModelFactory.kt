package com.kodo.friple.mvvm.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router

class MyViewModelFactory(val router: Router) : ViewModelProvider.Factory {

    var context: Context? = null

    constructor(router: Router, context: Context) : this(router) {
        this.context = context
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (context != null) {
            modelClass.getConstructor(Router::class.java, Context::class.java)
                .newInstance(router, context)
        } else {
            modelClass.getConstructor(Router::class.java)
                .newInstance(router)
        }
    }
}