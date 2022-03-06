package id.co.arya.kumparan.data.repository

import id.co.arya.kumparan.api.ApiService
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.local.AppDatabase

class MainRepository(private val appDatabase: AppDatabase) {

    suspend fun listPostApi() =
        ApiService().API_SERVICE.listPostApi()

    suspend fun listUserApi() =
        ApiService().API_SERVICE.listUserApi()

    suspend fun emptyTables() {
        appDatabase.dao().emptyTables()
    }

}