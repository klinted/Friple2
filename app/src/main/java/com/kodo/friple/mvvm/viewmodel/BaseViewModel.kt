package com.kodo.friple.mvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

open class BaseViewModel:  ViewModel() {

    val snackBarStatus = MutableLiveData<Boolean?>()
    val message = MutableLiveData<String>()
    val isLoading = ObservableField<Boolean>()
}