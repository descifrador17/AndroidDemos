package com.descifrador.retrofitdemo.model

import com.google.gson.annotations.SerializedName

class user(

    @SerializedName("id")
    val user_id : Int,

    @SerializedName("name")
    val user_name : String,

    val username : String,

    @SerializedName("email")
    val user_email : String

)