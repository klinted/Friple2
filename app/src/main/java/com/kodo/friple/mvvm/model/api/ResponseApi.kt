package com.kodo.friple.mvvm.model.api

import com.google.gson.annotations.SerializedName

class ResponseApi(){

    data class RegistrationResponse(
        @SerializedName("status")
        val status: Boolean,

        @SerializedName("user_id")
        val userId: Int?,

        @SerializedName("message")
        val message: String
    )

    data class LoginResponse(
        @SerializedName("status")
        val status: Boolean,

        @SerializedName("login")
        val login: String?,

        @SerializedName("message")
        val message: String
    )
}

