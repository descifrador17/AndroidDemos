package com.descifrador.jetpackcomposedemo

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBarDemo() {

    var progress by remember {
        mutableStateOf(0.5f)
    }

    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Indefinite Circular Progress Bar")
        Spacer(modifier = Modifier.height(30.dp))
        CircularProgressIndicator()

        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Definite Circular Progress Bar")
        Spacer(modifier = Modifier.height(30.dp))
        CircularProgressIndicator(progress = animatedProgress)
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            if (progress < 1.0f) {
                progress += 0.1f
            }
        }) {
            Text(text = "Increase")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            if (progress > 0.0f) {
                progress -= 0.1f
            }
        }) {
            Text(text = "Decrease")
        }

    }
}
