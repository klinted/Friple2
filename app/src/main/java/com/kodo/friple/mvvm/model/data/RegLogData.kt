package com.kodo.friple.mvvm.model.data

class RegLogData(){

    data class RegistrationBody(
        val login: String,

        val email: String,

        val password: String
    )

    data class LoginBody(

        val login: String,

        val password: String
    )
}