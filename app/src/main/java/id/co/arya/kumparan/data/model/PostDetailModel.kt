package id.co.arya.kumparan.data.model

import java.io.Serializable

data class PostDetailModel(
    val postId: String,
    val userId: String,
    val username: String,
    val postTitle: String,
    val postBody: String
) : Serializable
