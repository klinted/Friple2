package com.kodo.friple.mvvm.viewmodel

import androidx.activity.OnBackPressedCallback
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.kodo.friple.mvvm.common.navigation.BackButtonListener

open class BaseViewModel: ViewModel(){

    // For displays snackBar, starts "move" actions (to next screen) and ends animations
    val snackBarStatus = MutableLiveData<Boolean?>()
    // For start animation at button
    val animate = MutableLiveData<Boolean?>()
    // Message which gonna shows in snackBar
    val message = MutableLiveData<String>()
    // For hide or show bottom navigation
    val hideBottomNavigation = MutableLiveData<Boolean?>()

    // For set button's clickable
    val isLoading = ObservableField<Boolean>()

}