package com.kodo.friple.mvvm.model.retrofit

import android.util.Log
import com.kodo.friple.mvvm.model.api.RegLogApi
import com.kodo.friple.mvvm.model.api.ResponseApi
import com.kodo.friple.mvvm.model.data.RegLogData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationController : Callback<ResponseApi> {

    private lateinit var iResponseCallBack: ResponseRegCallBack

    lateinit var login: String
    lateinit var email: String
    lateinit var password: String

    lateinit var responseMessage: String

    fun start(regData: RegLogData.RegistrationBody, responseCallBack: ResponseRegCallBack) {
        //Check regData
        Log.d("RegistrationController", "$regData")

        // Set to variables value from regData
        login = regData.login
        email = regData.email
        password = regData.password

        // Here creates call and gets api client
        val call: Call<ResponseApi> =
            ApiClient.getApiClient().create(RegLogApi::class.java).regUser(login, email, password)
        call.enqueue(this)

        this.iResponseCallBack = responseCallBack
    }

    override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
        Log.d("RegistrationController", "onResponse method")
        Log.d("RegistrationController", "Response body: ${response.body()}")
        when (response.code()) {
            409 -> userExists(response.body())
            201 -> userWasReg(response.body())
            404 -> regErrors(response.body())
        }
    }

    override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
        Log.d("RegistrationController", "onFailure method")
    }

    private fun userExists(body: ResponseApi?) {
        Log.d("RegistrationController", "${body?.message} Status: ${body?.status}")
        responseMessage = "User with $login already exists!"
        iResponseCallBack.responseDataReady(false, responseMessage)
    }

    private fun userWasReg(body: ResponseApi?) {
        Log.d("RegistrationController", "${body?.message} Status: ${body?.status}")
        responseMessage = "Nice to meet you $login!"
        iResponseCallBack.responseDataReady(true, responseMessage)
    }

    private fun regErrors(body: ResponseApi?) {
        Log.d("RegistrationController", "${body?.message} Status: ${body?.status}")
        responseMessage = "Something is wrong! Please try again later."
        iResponseCallBack.responseDataReady(false, responseMessage)
    }

    interface ResponseRegCallBack {
        fun responseDataReady(regStatus: Boolean, responseMessage: String)
    }
}