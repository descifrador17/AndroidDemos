package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnackBarDemo() {

    var snackBarState by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(32.dp)) {
        Button(
            onClick = {
                snackBarState = !snackBarState
            }) {
            if (snackBarState) {
                Text(text = "Hide SnackBar")
            } else {
                Text(text = "Show SnackBar")
            }
        }
        if (snackBarState) {
            Snackbar(
                action = {
                    Button(onClick = { snackBarState = false }) {
                        Text(text = "Hide Snackbar")
                    }
                }) {
                Text(text = "This is a snackbar",modifier = Modifier.padding(16.dp))
            }
        }
    }
}
