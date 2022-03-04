package id.co.arya.kumparan.data.repository

import id.co.arya.kumparan.api.ApiService

class HomeRepository {

    suspend fun listPostApi() =
        ApiService().API_SERVICE.listPostApi()

}