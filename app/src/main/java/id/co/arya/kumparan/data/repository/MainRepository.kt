package id.co.arya.kumparan.data.repository

import id.co.arya.kumparan.api.ApiService

class MainRepository {

    suspend fun listPostApi() =
        ApiService().API_SERVICE.listPostApi()

    suspend fun listUserApi() =
        ApiService().API_SERVICE.listUserApi()

}