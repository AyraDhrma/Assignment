package id.co.arya.kumparan.data.repository

import id.co.arya.kumparan.api.ApiService

open class UserRepository {

    open suspend fun detailUserApi(userId: String) =
        ApiService().API_SERVICE.detailUserApi(userId)

    open suspend fun listAlbumsUserApi(userId: String) =
        ApiService().API_SERVICE.listAlbumsUserApi(userId)

    open suspend fun listAlbumsPhotosApi() =
        ApiService().API_SERVICE.listAlbumsPhotosApi()

}