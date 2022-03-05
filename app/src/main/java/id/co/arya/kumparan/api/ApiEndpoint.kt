package id.co.arya.kumparan.api

import id.co.arya.kumparan.data.model.*
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

    @GET("/users/{id}/albums")
    suspend fun listAlbumsUserApi(@Path("id") userId: String): AlbumsModel

//    @GET("/albums/{id}/photos")
//    suspend fun listAlbumsPhotosApi(@Path("id") userId: String): PhotosModel

    @GET("/photos")
    suspend fun listAlbumsPhotosApi():  ArrayList<PhotosModel.PhotosModelItem>

    @GET("/users/{id}")
    suspend fun detailUserApi(@Path("id") userId: String): UserModel.UserModelItem

}