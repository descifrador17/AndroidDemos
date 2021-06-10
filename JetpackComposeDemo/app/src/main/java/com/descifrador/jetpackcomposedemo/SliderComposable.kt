package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SliderDemo(){
    var sliderValue by remember {
        mutableStateOf(0f)
    }

    Column(modifier = Modifier.padding(32.dp)) {
        Text(text = sliderValue.toString())
        Spacer(modifier = Modifier.height(30.dp))
        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
            })
    }
}
