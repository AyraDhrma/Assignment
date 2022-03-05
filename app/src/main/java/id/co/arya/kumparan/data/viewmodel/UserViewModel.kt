package id.co.arya.kumparan.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.co.arya.kumparan.api.ResultApi
import id.co.arya.kumparan.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

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

}