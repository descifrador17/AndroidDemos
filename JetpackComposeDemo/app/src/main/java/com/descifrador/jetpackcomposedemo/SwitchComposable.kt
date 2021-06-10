package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SwitchDemo(){
    
    var selectState by remember {
        mutableStateOf(false)
    }
    
    Column(modifier = Modifier.padding(32.dp)) {
        Switch(
            checked = selectState,
            onCheckedChange = {
                selectState = !selectState
            })
    }
    
}
