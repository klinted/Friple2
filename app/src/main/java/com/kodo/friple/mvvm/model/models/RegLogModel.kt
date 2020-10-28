package com.kodo.friple.mvvm.model.models

import com.kodo.friple.mvvm.model.api.ResponseApi
import com.kodo.friple.mvvm.model.data.RegLogData
import com.kodo.friple.mvvm.model.retrofit.LogController
import com.kodo.friple.mvvm.model.retrofit.RegistrationController
import com.kodo.friple.mvvm.model.retrofit.RegistrationController.ResponseRegCallBack
import com.kodo.friple.mvvm.model.retrofit.LogController.ResponseLogCallBack

class RegLogModel {

    private val mRegController: RegistrationController = RegistrationController()
    private val mLogController: LogController = LogController()

    // This is method for sign up
    fun regUser(regData: RegLogData.RegistrationBody, onDataReadyCallback: OnDataReadyCallback) {
        mRegController.start(regData, object: ResponseRegCallBack {
            override fun responseDataReady(regStatus: Boolean, responseMessage: String) {
                onDataReadyCallback.onDataReady(regStatus, responseMessage)
            }
        })
    }

    // This is method for sign in
    fun logUser(logData: RegLogData.LoginBody, onDataReadyCallback: OnDataReadyCallback){
        mLogController.start(logData, object: ResponseLogCallBack{
            override fun responseDataReady(regStatus: Boolean, responseMessage: String) {
                onDataReadyCallback.onDataReady(regStatus, responseMessage)
            }

        })
    }

    interface OnDataReadyCallback {
        fun onDataReady(regStatus: Boolean, responseMessage: String)
    }
}