package com.descifrador.jetpackcomposedemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                greeting()
                greeting_state()
            }

        }
    }

    @Composable
    fun greeting(){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hello!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Name") }
            )
        }
    }

    @Composable
    fun greeting_state(){
        Column(modifier = Modifier.padding(bottom = 8.dp)){
            var name: String by remember {
                mutableStateOf("")
            }
            Text(
                text = "Hello! $name",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = { Text(text = "Name")}

            )
        }
    }
}
