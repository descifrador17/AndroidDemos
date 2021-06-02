package com.descifrador.mvpmodeldemo.presenter

import android.util.Log
import com.descifrador.mvpmodeldemo.model.User
import com.descifrador.mvpmodeldemo.view.ILoginView

class LoginPresenter(private val loginView : ILoginView) : ILoginPresenter {


    override fun onLogin(userEmail: String, userPass: String) {
        val user = User(userEmail,userPass)



        val isLoginSuccess = user.isValidData()

        if (isLoginSuccess)
            loginView.onLoginResult("Success")
        else
            loginView.onLoginResult("Failed")
    }
}