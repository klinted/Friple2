package com.kodo.friple.apputil

import android.content.Context
import android.content.SharedPreferences
import com.kodo.friple.R

class AppConfig(private var context: Context) {

    private var sharedPreferences: SharedPreferences = context
        .getSharedPreferences(context.getString(R.string.pref_file_key), Context.MODE_PRIVATE)

    fun isUserLogin(): Boolean = sharedPreferences
        .getBoolean(context.getString(R.string.pref_is_user_login), false)

    fun updateUserLoginStatus(status: Boolean){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(context.getString(R.string.pref_is_user_login), status)
        editor.apply()
    }

    fun saveNameOfUser(name: String){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(context.getString(R.string.pref_name_of_user), name)
        editor.apply()
    }

    fun getNameOfUser() = sharedPreferences
        .getString(context.getString(R.string.pref_name_of_user), "Unknown")
}