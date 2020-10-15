package com.kodo.friple.mvvm.model.api

import com.kodo.friple.mvvm.model.data.RegLogData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegLogApi {

    @FormUrlEncoded
    @POST("reg")
    fun regUser(@Field("login")login: String,
                @Field("email")email: String,
                @Field("password")password: String): Call<ResponseApi.RegistrationResponse>

    @FormUrlEncoded
    @POST("log")
    fun logUser(@Field("login")login: String,
                @Field("password")password: String,): Call<ResponseApi.LoginResponse>

}