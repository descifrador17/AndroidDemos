package com.descifrador.jetpackcomposedemo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
