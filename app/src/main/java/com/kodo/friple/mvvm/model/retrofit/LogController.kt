package com.kodo.friple.mvvm.model.retrofit

import android.util.Log
import com.kodo.friple.mvvm.model.api.RegLogApi
import com.kodo.friple.mvvm.model.api.ResponseApi
import com.kodo.friple.mvvm.model.data.RegLogData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogController : Callback<ResponseApi> {

    lateinit var iResponseCallBack: ResponseLogCallBack

    lateinit var login: String
    lateinit var password: String

    lateinit var responseMessage: String

    fun start(logData: RegLogData.LoginBody, responseCallBack: ResponseLogCallBack) {
        //Check logData
        Log.d("RegistrationController", "$logData")

        // Set to variables value from logData
        login = logData.login
        password = logData.password

        // Here creates call and gets api client
        val call: Call<ResponseApi> =
            ApiClient.getApiClient().create(RegLogApi::class.java).logUser(login, password)
        call.enqueue(this)

        this.iResponseCallBack = responseCallBack
    }

    override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
        Log.d("LoginController", "onResponse method")
        Log.d("LoginController", "Response body: ${response.body()}")
        when (response.code()) {
            200 -> successfullyLog(response.body())
            401 -> unsuccessfullyLog(response.body())
            404 -> logError(response.body())
        }
    }

    override fun onFailure(call: Call<ResponseApi>, t: Throwable) {

    }

    private fun successfullyLog(body: ResponseApi?) {
        Log.d("LoginController", "${body?.message} Status: ${body?.status}")
        responseMessage = "Welcome $login!"
        iResponseCallBack.responseDataReady(true, responseMessage)
    }

    private fun unsuccessfullyLog(body: ResponseApi?) {
        Log.d("LoginController", "${body?.message} Status: ${body?.status}")
        responseMessage = "The login details are incorrect!"
        iResponseCallBack.responseDataReady(false, responseMessage)
    }

    private fun logError(body: ResponseApi?) {
        Log.d("LoginController", "${body?.message} Status: ${body?.status}")
        responseMessage = "Something is wrong! Please try again later."
        iResponseCallBack.responseDataReady(false, responseMessage)
    }

    interface ResponseLogCallBack {
        fun responseDataReady(regStatus: Boolean, responseMessage: String)
    }
}