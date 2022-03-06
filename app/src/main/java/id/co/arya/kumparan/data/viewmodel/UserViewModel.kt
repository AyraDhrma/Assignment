package id.co.arya.kumparan.data.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import id.co.arya.kumparan.api.ResultApi
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.data.repository.UserRepository
import id.co.arya.kumparan.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    private val application: Application
) : ViewModel() {

    private lateinit var userRepository: UserRepository

    init {
        val appDatabase = AppDatabase.initDatabase(application)
        userRepository = UserRepository(appDatabase)
    }

    suspend fun detailUserApi(userId: String) = liveData(Dispatchers.IO) {
        emit(ResultApi.loading(data = null))
        try {
            emit(ResultApi.success(data = userRepository.detailUserApi(userId)))
        } catch (e: Exception) {
            emit(ResultApi.error(data = null, message = e.localizedMessage ?: "Error Connection"))
        }
    }

    suspend fun listAlbumsUserApi(userId: String) = liveData(Dispatchers.IO) {
        emit(ResultApi.loading(data = null))
        try {
            emit(ResultApi.success(data = userRepository.listAlbumsUserApi(userId)))
        } catch (e: Exception) {
            emit(ResultApi.error(data = null, message = e.localizedMessage ?: "Error Connection"))
        }
    }

    suspend fun listAlbumsPhotosApi() = liveData(Dispatchers.IO) {
        emit(ResultApi.loading(data = null))
        try {
            emit(ResultApi.success(data = userRepository.listAlbumsPhotosApi()))
        } catch (e: Exception) {
            emit(ResultApi.error(data = null, message = e.localizedMessage ?: "Error Connection"))
        }
    }

    fun insertPhotos(listPhotosModel: LocalPhotosModel) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertPhotosAlbumsToRoom(listPhotosModel)
        }
    }

    fun selectPhotosByAlbums(albumId: Int): LocalPhotosModel {
        return userRepository.selectPhotosByAlbums(albumId)
    }

}