package id.co.arya.kumparan.data.repository

import id.co.arya.kumparan.api.ApiService

open class MainRepository {

    open suspend fun listPostApi() =
        ApiService().API_SERVICE.listPostApi()

    open suspend fun listUserApi() =
        ApiService().API_SERVICE.listUserApi()

}