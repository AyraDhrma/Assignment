package id.co.arya.kumparan.api

import id.co.arya.kumparan.data.model.PostCommentsModel
import id.co.arya.kumparan.data.model.PostModel
import id.co.arya.kumparan.data.model.UserModel
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoint {

    @GET("/posts")
    suspend fun listPostApi(): PostModel

    @GET("/users")
    suspend fun listUserApi(): UserModel

    @GET("/posts/{id}/comments")
    suspend fun listPostCommentsApi(@Path("id") postId: String): PostCommentsModel

}