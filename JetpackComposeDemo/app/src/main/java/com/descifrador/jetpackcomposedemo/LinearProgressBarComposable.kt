package com.descifrador.jetpackcomposedemo

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LinearProgressBarDemo(){

    var progress by remember {
        mutableStateOf(0.5f)
    }

    val animatedProgress = animateFloatAsState(targetValue = progress, animationSpec =
    ProgressIndicatorDefaults.ProgressAnimationSpec).value

    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Indefinite Linear Progress Bar")
        LinearProgressIndicator()
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Definite Linear Progress Bar")
        LinearProgressIndicator(progress = animatedProgress)
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { if (progress < 1.0f){
            progress += 0.1f
        }
        }) {
            Text(text = "Increase")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { if (progress > 0.0f){
            progress -= 0.1f
        }
        }) {
            Text(text = "Decrease")
        }

    }

}
