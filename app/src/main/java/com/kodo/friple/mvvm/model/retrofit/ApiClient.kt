package com.kodo.friple.mvvm.model.retrofit

import com.kodo.friple.mvvm.model.api.ResponseApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val BASE_URL = "https://friple.ru/api/"
    private var retrofit: Retrofit? = null

    fun getApiClient(): Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }
}