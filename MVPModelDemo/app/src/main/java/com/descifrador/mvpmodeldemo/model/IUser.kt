package com.descifrador.mvpmodeldemo.model

interface IUser {

    fun getEmail() : String
    fun getPassword() : String
    fun isValidData() : Boolean

}