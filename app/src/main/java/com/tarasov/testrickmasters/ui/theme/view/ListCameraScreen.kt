package com.tarasov.testrickmasters.ui.theme.view


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
//import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tarasov.testrickmasters.presentation.MainViewModel


@Composable
fun ListCameraScreen(viewModel: MainViewModel) {

    Column(modifier = Modifier.fillMaxSize().draggable(
        state = viewModel.dragState.value!!,
        orientation = Orientation.Horizontal,
        onDragStarted = {  },
        onDragStopped = {
            viewModel.updateTabIndexBasedOnSwipe()
        }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "Камеры",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}