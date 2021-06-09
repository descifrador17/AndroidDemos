package com.descifrador.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.descifrador.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DropDownMenuDemo()
                }
            }
        }
    }
}

@Composable
fun CardDemo() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), elevation = 8.dp
    ) {
        Text("Hello", modifier = Modifier.padding(16.dp))
    }
}

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
