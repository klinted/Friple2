package com.kodo.friple.mvvm.viewmodel

import androidx.activity.OnBackPressedCallback
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.kodo.friple.mvvm.common.navigation.BackButtonListener

open class BaseViewModel: ViewModel(){

    val snackBarStatus = MutableLiveData<Boolean?>()
    val animate = MutableLiveData<Boolean?>()
    val message = MutableLiveData<String>()
    val hideBottomNavigation = MutableLiveData<Boolean?>()

    val isLoading = ObservableField<Boolean>()

}