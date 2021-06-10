package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonDemo(){

    val options = listOf("A","B","C")

    var selectedOption by remember {
        mutableStateOf(options[1])
    }

    Column(modifier = Modifier.padding(16.dp)) {

        options.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            selectedOption = text
                        }
                    )
            ){
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { 
                        selectedOption = text 
                    })
                
                Text(text = text)
            } 
        }
    }
}
