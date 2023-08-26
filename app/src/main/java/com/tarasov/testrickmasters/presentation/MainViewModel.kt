package com.tarasov.testrickmasters.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarasov.testrickmasters.data.network.camera.CameraRepositoryImpl
import com.tarasov.testrickmasters.data.network.door.DoorRepositoryImpl
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.presentation.utils.SimpleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryCameras: CameraRepositoryImpl,
    private val repositoryDoor: DoorRepositoryImpl,
   // private val dispatcherIO: CoroutineDispatcher  // нужно создать аннотацию для Хилта
    ): ViewModel() {

    private val _viewState = MutableLiveData<SimpleState<List<CameraEntity>>>()
    val viewState: LiveData<SimpleState<List<CameraEntity>>> get() = _viewState

    fun getCamerasRemote() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repositoryCameras.getCameraRemote()) {
                is NetworkResponse.Success -> {
                    val camerasEntities = response.data
                    Log.d("MY_LOG", camerasEntities[0].name)
                    _viewState.postValue(SimpleState.Success(camerasEntities))
                }
                is NetworkResponse.Error -> {
                    Log.d("MY_LOG", response.responseCode.code.toString())
                    _viewState.postValue(SimpleState.Error(response.responseCode))
                }
            }
        }
    }

    fun getRoomsRemote() {
        viewModelScope.launch {
            val x = repositoryDoor.getDoorRemote()
        }
    }
}