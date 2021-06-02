package com.descifrador.mvpmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.descifrador.mvpmodeldemo.presenter.ILoginPresenter
import com.descifrador.mvpmodeldemo.presenter.LoginPresenter
import com.descifrador.mvpmodeldemo.view.ILoginView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() , ILoginView {

    private lateinit var loginPresenter : ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loginPresenter = LoginPresenter(this)

        val btnClickMe = findViewById<Button>(R.id.loginButton)

        btnClickMe.setOnClickListener{
            loginPresenter.onLogin(emailText.text.toString(),PassText.text.toString())
        }

    }

    override fun onLoginResult(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}