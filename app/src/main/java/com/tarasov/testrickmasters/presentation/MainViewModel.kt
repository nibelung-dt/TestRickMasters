package com.tarasov.testrickmasters.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarasov.testrickmasters.data.network.camera.CameraRepositoryImpl
import com.tarasov.testrickmasters.data.network.door.DoorRepositoryImpl
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.di.IoDispatcher
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.domain.door.DoorEntity
import com.tarasov.testrickmasters.presentation.utils.SimpleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryCameras: CameraRepositoryImpl,
    private val repositoryDoor: DoorRepositoryImpl,
    @IoDispatcher private val dispatcherIO: CoroutineDispatcher
    ): ViewModel() {

    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs = listOf("Камеры", "Двери")


    private val _viewStateCameras = MutableLiveData<SimpleState<List<CameraEntity>>>()
    val viewStateCameras: LiveData<SimpleState<List<CameraEntity>>> get() = _viewStateCameras

    private val _viewStateDoors = MutableLiveData<SimpleState<List<DoorEntity>>>()
    val viewStateDoors: LiveData<SimpleState<List<DoorEntity>>> get() = _viewStateDoors


    fun getCameras() {
        viewModelScope.launch(dispatcherIO) {
            when (val response = repositoryCameras.getCamera()) {
                is NetworkResponse.Success -> {
                    val camerasEntities = response.data
                    Log.d("MY_LOG", camerasEntities[0].name)
                    _viewStateCameras.postValue(SimpleState.Success(camerasEntities))
                }
                is NetworkResponse.Error -> {
                    Log.d("MY_LOG", response.responseCode.code.toString())
                    _viewStateCameras.postValue(SimpleState.Error(response.responseCode))
                }
            }
        }
    }

    fun getRooms() {
        viewModelScope.launch(dispatcherIO) {
            when (val response = repositoryDoor.getDoor()) {
                is NetworkResponse.Success -> {
                    val doorsEntities = response.data
                    _viewStateDoors.postValue(SimpleState.Success(doorsEntities))
                }
                is NetworkResponse.Error -> {
                    Log.d("MY_LOG", response.responseCode.code.toString())
                    _viewStateDoors.postValue(SimpleState.Error(response.responseCode))
                }
            }
        }
    }
}