package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun DropDownMenuDemo(){
    Column{

        var expanded by remember {
            mutableStateOf(false)
        }

        val items = listOf("Bareilly", "Delhi", "Kurukshetra", "Hyderabad", "Bangalore")
        var selectedIndex by remember {
            mutableStateOf(0)
        }

        Text(text = items[selectedIndex], modifier = Modifier.clickable { expanded = true })

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false}) {
                    Text(text = s)
                }
            }
        }
    }
}
