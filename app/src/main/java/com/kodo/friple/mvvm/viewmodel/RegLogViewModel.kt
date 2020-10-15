package com.kodo.friple.mvvm.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.kodo.friple.mvvm.model.data.RegLogData
import com.kodo.friple.mvvm.model.models.RegLogModel

class RegLogViewModel: BaseViewModel() {

    var regLogModel: RegLogModel = RegLogModel()

    val login = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()

    fun refresh(){

        val regData: RegLogData.RegistrationBody =
            RegLogData.RegistrationBody(login.get()!!, email.get()!!, password.get()!!)

        Log.d("VM", "RegData: $regData")
        isLoading.set(true)
        regLogModel.refreshData(regData,
            object : RegLogModel.OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                status.value = true
            }
        })
    }
}