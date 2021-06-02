package com.descifrador.mvpmodeldemo.model

import android.text.TextUtils
import android.util.Log
import android.util.Patterns

class User(private val userEmail : String, private val userPassword : String) : IUser{

    override fun getEmail(): String {
        return userEmail
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun isValidData(): Boolean {

        return !TextUtils.isEmpty(getEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() &&
                (getPassword().length >= 6)
    }
}