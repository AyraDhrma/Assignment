package id.co.arya.kumparan.api

import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.data.model.UserModel
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("/posts")
    suspend fun listPostApi(): PostModel

    @GET("/users")
    suspend fun listUserApi(): UserModel

}