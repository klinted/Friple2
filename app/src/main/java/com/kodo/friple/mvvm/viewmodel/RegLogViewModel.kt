package com.kodo.friple.mvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Router
import com.kodo.friple.apputil.AppConfig
import com.kodo.friple.mvvm.common.Screens.Profile
import com.kodo.friple.mvvm.model.data.RegLogData
import com.kodo.friple.mvvm.model.models.RegLogModel
import com.kodo.friple.mvvm.model.models.RegLogModel.OnDataReadyCallback
import com.kodo.friple.mvvm.view.activities.MainActivity

class RegLogViewModel(val router: Router, val context: Context): BaseViewModel() {

    // For control background of button
    val isSelectedBackground = ObservableField<Boolean>()

    var mRegLogModel: RegLogModel = RegLogModel()

    private val mAppConfig: AppConfig = AppConfig(context)

    // Here we gets strings from xml
    val login = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()

    fun startReg(){
        // Change string variables to data class
        val regData: RegLogData.RegistrationBody =
            RegLogData.RegistrationBody(login.get()!!, email.get()!!, password.get()!!)

        // Here we set true for variables and observers will be react
        isLoading.set(true)
        animate.value = true

        mRegLogModel.regUser(regData,
            object : OnDataReadyCallback {
                override fun onDataReady(
                    regStatus: Boolean,
                    responseMessage: String,
                    login: String
                ) {
                    prefUpdate(regStatus, login)

                    isLoading.set(false)
                    message.value = responseMessage
                    snackBarStatus.value = true
                }
            })
    }

    fun startLog(){
        val logData: RegLogData.LoginBody =
            RegLogData.LoginBody(login.get()!!, password.get()!!)

        Log.d("VM", "LogData: $logData")
        isLoading.set(true)
        animate.value = true

        mRegLogModel.logUser(logData,
            object : OnDataReadyCallback {
                override fun onDataReady(
                    regStatus: Boolean,
                    responseMessage: String,
                    login: String
                ) {
                    prefUpdate(regStatus, login)

                    isLoading.set(false)
                    message.value = responseMessage
                    snackBarStatus.value = true
                }
            })
    }

    private fun prefUpdate(status: Boolean, login: String) {
        if (status){
            mAppConfig.updateUserLoginStatus(status)
            mAppConfig.saveNameOfUser(login)
            Log.d("fff", "${mAppConfig.getNameOfUser()}")
        } else {
            mAppConfig.updateUserLoginStatus(status)
        }
    }

    fun openProfile(){
        if (mAppConfig.isUserLogin()) router.newRootScreen(Profile("profile", 1))
    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onCleared() {
        hideBottomNavigation.value = false
        super.onCleared()
    }

}