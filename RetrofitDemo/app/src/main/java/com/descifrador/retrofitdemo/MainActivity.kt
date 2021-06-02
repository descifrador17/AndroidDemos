package com.descifrador.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.descifrador.retrofitdemo.model.user
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val jsonplaceholderApi = retrofit.create(JsonplaceholderApi::class.java)

        val myCall : Call<List<user>> = jsonplaceholderApi.getUsers()

        myCall.enqueue(object : Callback<List<user>>{
            override fun onFailure(call: Call<List<user>>, t: Throwable) {
                print("Errorrrrr")
            }

            override fun onResponse(call: Call<List<user>>, response: Response<List<user>>) {

                val user_list : List<user> = response.body()!!

                val stringBuilder = StringBuilder()
                for(i in user_list){
                    stringBuilder.append("User ID : ${i.user_id}\n")
                    stringBuilder.append("User Name : ${i.user_name}\n")
                    stringBuilder.append("Username : ${i.username} \n")
                    stringBuilder.append("User Email: ${i.user_email} \n\n")

                }

                textView.text = stringBuilder
            }

        })

    }
}
