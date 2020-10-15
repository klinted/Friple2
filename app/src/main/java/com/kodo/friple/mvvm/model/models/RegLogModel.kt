package com.kodo.friple.mvvm.model.models

import android.os.Handler
import com.kodo.friple.mvvm.model.data.RegLogData
import com.kodo.friple.mvvm.model.retrofit.RegistrationController

class RegLogModel {

    private val regController: RegistrationController = RegistrationController()

    fun refreshData(regData: RegLogData.RegistrationBody, onDataReadyCallback: OnDataReadyCallback) {
        regController.start(regData)
        onDataReadyCallback.onDataReady("new data")
    }

    interface OnDataReadyCallback {
        fun onDataReady(data: String)
    }
}