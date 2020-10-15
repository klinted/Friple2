package com.kodo.friple.mvvm.model.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kodo.friple.mvvm.model.api.RegLogApi
import com.kodo.friple.mvvm.model.api.ResponseApi
import com.kodo.friple.mvvm.model.data.RegLogData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationController() : Callback<ResponseApi.RegistrationResponse> {

    fun start(regData: RegLogData.RegistrationBody){

        val login = regData.login
        val email = regData.email
        val password = regData.password

        val call: Call<ResponseApi.RegistrationResponse> =
            ApiClient.getApiClient().create(RegLogApi::class.java).regUser(login, email, password)
        call.enqueue(this)
        Log.d("fff", "data: $regData")
    }

    override fun onResponse(
        call: Call<ResponseApi.RegistrationResponse>,
        response: Response<ResponseApi.RegistrationResponse>
    ) {
        when(response.code()){
            201 -> created(response)
            404 -> Log.d("fff", "${response.body()}")
            403 -> Log.d("fff", "User exists!")
        }
    }

    private fun created(response: Response<ResponseApi.RegistrationResponse>) {
        if(response.body()!!.status){
            Log.d("fff", "status: ${response.body()!!.status}")
            Log.d("fff", "User was registered!")
        } else {
            Log.d("fff", "Something wrong")
        }
    }

    override fun onFailure(call: Call<ResponseApi.RegistrationResponse>, t: Throwable) {
        TODO("Not yet implemented")
    }
}