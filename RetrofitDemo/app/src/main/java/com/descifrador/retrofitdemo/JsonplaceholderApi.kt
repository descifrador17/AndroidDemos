package com.descifrador.retrofitdemo

import com.descifrador.retrofitdemo.model.user
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonplaceholderApi {

    //https://jsonplaceholder.typicode.com/users

    @GET("users")
    fun getUsers() : Call<List<user>>

    @POST("posts")
    fun senduserData() : Call<user>
}