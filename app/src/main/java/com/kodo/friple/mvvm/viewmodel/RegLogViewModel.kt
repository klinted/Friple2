package com.kodo.friple.mvvm.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.kodo.friple.mvvm.model.api.ResponseApi
import com.kodo.friple.mvvm.model.data.RegLogData
import com.kodo.friple.mvvm.model.models.RegLogModel
import com.kodo.friple.mvvm.model.models.RegLogModel.OnDataReadyCallback

class RegLogViewModel: BaseViewModel() {

    var mRegLogModel: RegLogModel = RegLogModel()

    val login = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()

    fun startReg(){
        val regData: RegLogData.RegistrationBody =
            RegLogData.RegistrationBody(login.get()!!, email.get()!!, password.get()!!)

        Log.d("VM", "RegData: $regData")
        isLoading.set(true)
        mRegLogModel.regUser(regData,
            object : OnDataReadyCallback {
            override fun onDataReady(regStatus: Boolean, responseMessage: String) {
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
        mRegLogModel.logUser(logData,
            object : OnDataReadyCallback {
            override fun onDataReady(regStatus: Boolean, responseMessage: String) {
                isLoading.set(false)
                message.value = responseMessage
                snackBarStatus.value = true
            }
        })
    }
}