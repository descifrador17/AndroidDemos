package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextViewDemo(){
    var name by remember {
        mutableStateOf("")
    }
    
    Column(modifier = Modifier.padding(32.dp)) {
        
        Text(text = "Hello! $name",style = MaterialTheme.typography.h5)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        TextField(
            value = name,
            onValueChange = {
                name = it
        })
    }
}
