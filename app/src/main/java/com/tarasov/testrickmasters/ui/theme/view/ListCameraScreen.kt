package com.tarasov.testrickmasters.ui.theme.view


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.presentation.MainViewModel
import com.tarasov.testrickmasters.presentation.utils.SimpleState


@Composable
fun ListCameraScreen(viewModel: MainViewModel) {

    Column(modifier = Modifier
        .fillMaxSize()
        .draggable(
            state = viewModel.dragState.value!!,
            orientation = Orientation.Horizontal,
            onDragStarted = { },
            onDragStopped = {
                viewModel.updateTabIndexBasedOnSwipe()
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        CamerasObserverCompose(viewModel)
    }
}

@Composable
fun CamerasObserverCompose(viewModel: MainViewModel) {
    when (val listCameras = viewModel.viewStateCameras.observeAsState().value) {
        is SimpleState.Success -> ShowLazyListCameras(listCameras.data)
        is SimpleState.Error -> Log.d("MY_LOG", "ошибка")
        is SimpleState.Loading -> Log.d("MY_LOG", "загрузка")
        else -> Log.d("MY_LOG", "else")
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getCameras()
    }
}

@Composable
fun ShowLazyListCameras(cameras: List<CameraEntity>) {
    LazyColumn {
        items(cameras) { cameras ->
            CardItemCamera(cameras)
        }
    }
}

@Composable
fun CardItemCamera(camera: CameraEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp)

    ) {
        Box(modifier = Modifier
            .fillMaxSize()
          //  .pullRefresh(pullRefreshState)
        ) {
            Column {
                Text(
                    text = "Номер камеры: " + camera.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Комната: " + camera.room,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Добавлено в избранное: " + camera.favorites.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "rec: " + camera.rec.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                GlideImage(
                    imageModel = { camera.snapshot },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
            }
        }
    }
}