package com.tarasov.testrickmasters.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarasov.testrickmasters.data.database.TestObject
import com.tarasov.testrickmasters.data.network.camera.CameraRepositoryImpl
import com.tarasov.testrickmasters.data.network.door.DoorRepositoryImpl
import com.tarasov.testrickmasters.data.network.utils.NetworkResponse
import com.tarasov.testrickmasters.domain.camera.CameraEntity
import com.tarasov.testrickmasters.domain.door.DoorEntity
import com.tarasov.testrickmasters.presentation.utils.SimpleState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryCameras: CameraRepositoryImpl,
    private val repositoryDoor: DoorRepositoryImpl,
   // private val dispatcherIO: CoroutineDispatcher  // нужно создать аннотацию для Хилта
    ): ViewModel() {

    private val _viewStateCameras = MutableLiveData<SimpleState<List<CameraEntity>>>()
    val viewStateCameras: LiveData<SimpleState<List<CameraEntity>>> get() = _viewStateCameras

    private val _viewStateDoors = MutableLiveData<SimpleState<List<DoorEntity>>>()
    val viewStateDoors: LiveData<SimpleState<List<DoorEntity>>> get() = _viewStateDoors


    val allNotes: LiveData<List<TestObject>> get() = getAllNotes()

    private var realm: Realm = Realm.getDefaultInstance()

    fun addTestObject(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val realm: Realm = Realm.getDefaultInstance()
            realm.executeTransaction { r: Realm ->
           // Realm.getDefaultInstance().executeTransaction { r: Realm ->
                val testObject = r.createObject(TestObject::class.java, UUID.randomUUID().toString())
                testObject.name = name
                realm.insertOrUpdate(testObject)
                Log.d("MY_LOG", "объект добавлен")
            }
         //   realm.close()  нужно ли это?
        }
    }

    private fun getAllNotes(): MutableLiveData<List<TestObject>> {
        val list = MutableLiveData<List<TestObject>>()
        val notes = realm.where(TestObject::class.java).findAll()
        list.value = notes?.subList(0, notes.size)
        return list
    }



    fun getCamerasRemote() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repositoryCameras.getCameraRemote()) {
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

    fun getRoomsRemote() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repositoryDoor.getDoorRemote()) {
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