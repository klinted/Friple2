package com.kodo.friple.mvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:  ViewModel() {

    var status = MutableLiveData<Boolean>(false)
    val isLoading = ObservableField<Boolean>()
}