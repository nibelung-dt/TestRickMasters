package com.tarasov.testrickmasters.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.domain.door.DoorEntity
import com.tarasov.testrickmasters.presentation.utils.SimpleState
import com.tarasov.testrickmasters.ui.theme.TestRickMastersTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestRickMastersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        viewModel.getCamerasRemote()
        viewModel.getRoomsRemote()
        camerasObserver()
        doorsObserver()
    }

    private fun camerasObserver() {
        val nameObserver = Observer<SimpleState<List<CameraEntity>>> { state ->
            when (state) {
                is SimpleState.Success -> {
                    val x = state.data
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestRickMastersTheme {
        Greeting("Android")
    }
}