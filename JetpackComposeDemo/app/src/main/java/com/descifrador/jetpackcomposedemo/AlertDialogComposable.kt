package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.descifrador.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

@Composable
fun AlertDialogDemo() {
    JetpackComposeDemoTheme {
        Column {

            val openDialog = remember {
                mutableStateOf(false)
            }

            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Click Me to open Dialog Box")
            }

            if (openDialog.value) {

                AlertDialog(onDismissRequest = {
                    openDialog.value = false
                }, title = {
                    Text(text = "Sample Dialog Box")
                }, text = {
                    Text(text = "This is sample text in dialog box")
                }, confirmButton = {
                    Button(onClick = {
                        openDialog.value = false
                    }) {
                        Text(text = "Confirm Button")
                    }
                }, dismissButton = {
                    Button(onClick = {
                        openDialog.value = false
                    }) {
                        Text(text = "Dismiss Button")
                    }

                })
            }
        }
    }
}
