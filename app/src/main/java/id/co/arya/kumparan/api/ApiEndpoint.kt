package id.co.arya.kumparan.api

import id.co.arya.kumparan.data.model.PostModel
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("/posts")
    suspend fun listPostApi(): PostModel

}