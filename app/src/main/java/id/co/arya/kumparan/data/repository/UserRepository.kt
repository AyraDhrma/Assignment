package id.co.arya.kumparan.data.repository

import androidx.lifecycle.LiveData
import id.co.arya.kumparan.api.ApiService
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.local.AppDatabase

class UserRepository(private val appDatabase: AppDatabase) {

    suspend fun detailUserApi(userId: String) =
        ApiService().API_SERVICE.detailUserApi(userId)

    suspend fun listAlbumsUserApi(userId: String) =
        ApiService().API_SERVICE.listAlbumsUserApi(userId)

    suspend fun listAlbumsPhotosApi() =
        ApiService().API_SERVICE.listAlbumsPhotosApi()

    suspend fun insertPhotosAlbumsToRoom(listPhotosModel: LocalPhotosModel) {
        appDatabase.dao().insertPhotos(listPhotosModel)
    }

    fun selectPhotosByAlbums(albumId: Int): LocalPhotosModel  =
        appDatabase.dao().selectPhotosByAlbum(albumId)

}