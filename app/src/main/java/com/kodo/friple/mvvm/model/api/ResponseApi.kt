package com.kodo.friple.mvvm.model.api

import com.google.gson.annotations.SerializedName

data class ResponseApi(

        @SerializedName("status")
        var status: Boolean,

        @SerializedName("message")
        val message: String
)

