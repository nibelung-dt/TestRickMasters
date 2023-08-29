package com.tarasov.testrickmasters.presentation


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.Observer
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
//import com.bumptech.glide.integration.compose.Placeholder
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.domain.door.DoorEntity
import com.tarasov.testrickmasters.presentation.utils.SimpleState
import com.tarasov.testrickmasters.ui.theme.TestRickMastersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var listCameras: List<CameraEntity> // = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestRickMastersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Header("Мой дом")
                        TabScreen()
                        CamerasObserverCompose(viewModel)
                    }
                }
            }
        }
        viewModel.getCameras()
        viewModel.getRooms()
        doorsObserver()
        camerasObserver()
    }

// observeAsState()
    // https://dev.to/mahendranv/using-viewmodel-livedata-with-jetpack-compose-31h8
    @Composable
    fun CamerasObserverCompose(viewModel: MainViewModel) {
        // State
        val listCameras = viewModel.viewStateCameras.observeAsState().value

        if (listCameras is SimpleState.Success) {
            ShowLazyList(listCameras.data)

            // UI
//            LazyColumn {
//               for (i in listCameras.data) {
//                   Text(i.name)
//               }
//            }
        }

        // API call
        LaunchedEffect(key1 = Unit) {
            viewModel.getCameras()
        }



//        when (val state =  viewModel.viewStateCameras.value) {
//            is SimpleState.Success -> {
//                ShowLazyList(state.data)
//            } else -> {
//                Log.d("MY_LOG", "CamerasObserverCompose() ошибка")
//                Log.d("MY_LOG", state.toString())
//            }
//        }

    }


    private fun camerasObserver() {



        val nameObserver = Observer<SimpleState<List<CameraEntity>>> { state ->
            when (state) {
                is SimpleState.Success -> {
                    listCameras = state.data
                    //creditProductsRvAdapter.submitList(state.data)
                }

                is SimpleState.Error -> {
                    Log.d("MY_LOG", "ошибка")
                }

                is SimpleState.Loading -> {
                    Log.d("MY_LOG", "загрузка")
                }
            }
        }
        viewModel.viewStateCameras.observe(this, nameObserver)
    }

    private fun doorsObserver() {
        val nameObserver = Observer<SimpleState<List<DoorEntity>>> { state ->
            when (state) {
                is SimpleState.Success -> {
                    val x = state.data


                    Log.d("MY_LOG", x[0].name)
                    //creditProductsRvAdapter.submitList(state.data)
                }

                is SimpleState.Error -> {
                    Log.d("MY_LOG", "ошибка roomsObserver()")
                }

                is SimpleState.Loading -> {
                    Log.d("MY_LOG", "загрузка")
                }
            }
        }
        viewModel.viewStateDoors.observe(this, nameObserver)
    }
}

@Composable
fun Header(name: String, modifier: Modifier = Modifier) {
    Log.d("MY_LOG", "Header() запущена")
    Text(
        text = name, fontSize = 25.sp, textAlign = TextAlign.Center,
        modifier = modifier,
    )
}


@Composable
fun ShowLazyList(cameras: List<CameraEntity>) {
    Log.d("MY_LOG", "ShowLazyList() запущена")
    LazyColumn {
        items(cameras) { cameras ->
            CardItem(cameras)
        }
    }
}



//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItem(camera: CameraEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ) {
        Log.d("MY_LOG", "CardItem() запущена")
        Box(modifier = Modifier.fillMaxSize()) {

            Text(
                text = camera.name,
                modifier = Modifier
                    .align(Alignment.TopCenter)
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

            Text(
                text = camera.room,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .background(Color.Gray),
                textAlign = TextAlign.Center,
                color = Color.White
            )

            Text(
                text = camera.favorites.toString(),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Gray),
                textAlign = TextAlign.Left,
                color = Color.White
            )

            Text(
                text = camera.rec.toString(),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .background(Color.Gray),
                textAlign = TextAlign.Right,
                color = Color.White
            )
        }

    }
}

@Composable
@UiComposable
fun TabRow(
    selectedTabIndex: Int,
    tabs: @Composable @UiComposable () -> Unit
): Unit {

}

@Composable
fun TabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Home", "About")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
            when (tabIndex) {
                0 -> ListCamerasColumn()
                1 -> ListTestColumn()
            }

    }
}


@Composable
fun ListCamerasColumn() {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.width(200.dp)) {
        for (i in 0..2)
            Text(i.toString())
        }
}

@Composable
fun ListTestColumn() {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.width(200.dp)) {
        for (i in 0..2)
            Text(i.toString())
    }
}



@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    TestRickMastersTheme {
        Header("Мой дом")
    }
}